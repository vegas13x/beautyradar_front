package com.nick_sib.beauty_radar.provider.auth_

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.ui.utils.*
import java.util.concurrent.TimeUnit

/**
 * @author Alex Volkov(Volkoks)
 *
 * Класс-реализация аутентификации и регистрации пользователя в приложении через Firebase.
 *
 * В данном классе выполняются такие действия как:
 * - Создание локальной liveData для связи и
 * передачи сигналов о результатах ругистрации/аутентификации.
 * - Выполняетя регистрация нового пользователя по средстсвом телефона и отправки проверочного кода
 * телефон указанный пользователем.
 * - Добавление email/password в уч.запись пользователя на Firebase.
 * - Вход в приложение через созданую уч.запись по средством email/password.
 */
class AuthProviderImpl(private val authUser: FirebaseAuth) : IAuthProvider {

    private val TAG_SUCCESS_VERIFICATION: String = "AuthPI SUCCESS"
    private val TAG_CODE_SEND: String = "AuthPI CODE SEND"

    private val livedataAuthProvider: MutableLiveData<AppState> = MutableLiveData()
    private lateinit var localVerificationId: String
    private var resendingToken: PhoneAuthProvider.ForceResendingToken? = null

    private val callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                singnInWithPhoneAuthCredential(phoneAuthCredential)
                Log.d(TAG_SUCCESS_VERIFICATION, "onVerificationCompleted: succes")
            }

            override fun onVerificationFailed(firebaseException: FirebaseException) {
                livedataAuthProvider.value = AppState.Loading(CODE_ERROR_GONE_CODE_LAYOUT)
                livedataAuthProvider.value = AppState.Error(firebaseException.message.toString())
            }

            override fun onCodeSent(
                verifyID: String,
                forceResendingToken: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verifyID, forceResendingToken)
                Log.d(TAG_CODE_SEND, "onCodeSent: ${verifyID}")
                localVerificationId = verifyID
                resendingToken = forceResendingToken
                livedataAuthProvider.value = AppState.Loading(CODE_RECEIVED_VISIBLE_CODE_LAYOUT)
                livedataAuthProvider.value = AppState.Success(localVerificationId)
            }
        }

    /**
     * Метод подписки на локальную liveData провайдера(класса)
     */
    override fun getLiveDataAuthProvider(): LiveData<AppState> {
        return livedataAuthProvider
    }

    /**
     * Регистрация пользователя по средством email/password
     */
    override fun singUpEmailAndPasswordUser(email: String, password: String) {
        authUser.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.w("ADD-FIREBASE", "Success add user in firebase")
                } else {
                    Log.w("ADD-FIREBASE", "ERROR add user in firebase")
                }
            }
    }

    /**
     *Старт регистрации по телефону: создаем настройки для кода - отправляем на сервер ,
     * ждём ответ и обрабатывает посредством callback для аутентификатора телефона
     */
    override fun startPhoneNumberVerification(activity: Activity, phone: String) {
        val options = PhoneAuthOptions.newBuilder(authUser)
            .setPhoneNumber(phone)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    /**
     * Повторный запрос кода с использование полученого токена от первичного запроса
     */
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun resentVerificationCode(activity: Activity, phone: String) {
        val options = PhoneAuthOptions.newBuilder(authUser)
            .setPhoneNumber(phone)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for callback binding)
            .setForceResendingToken(resendingToken)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    /**
     * Подтверждение кода
     */
    override fun verifyPhoneNumber(code: String) {
        val credential: PhoneAuthCredential =
            PhoneAuthProvider.getCredential(localVerificationId, code)
        singnInWithPhoneAuthCredential(credential)
    }

    /**
     * Добавление почты/пароля к уч.записи телефона.
     * Сделано для того что по телефону происходит только регистрация а вход через почту/пароль
     */
    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun addEmailAndPasswordInCurrentUser(email: String, password: String) {
        val credential = EmailAuthProvider.getCredential(email, password)
        authUser.currentUser.linkWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    livedataAuthProvider.value = AppState.Loading(EMAIL_ENTRY_OPEN_LOGOUT)
                } else {
                    livedataAuthProvider.value = AppState.Error("Данные пользователя не обновлены!")
                }
            }
    }

    /**
     * Функция выхода из учетной записи
     */
    override fun signOut() {
        authUser.signOut()
        livedataAuthProvider.value = AppState.Success(USER_SIGNOUT)
    }

    /**
     * Функция входа с помощью почты/пароля
     */
    override fun signInEmailPassword(email: String, password: String) {
        authUser.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    livedataAuthProvider.value = AppState.Success(
                        EMAIL_AND_PASSWORD_SUCCESS_GO_TO_LOGOUT
                    )
                } else {
                    livedataAuthProvider.value =
                        AppState.Error("Пользователя не существует или неверно введены данные")
                }
            }
    }

    private fun singnInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        authUser.signInWithCredential(credential)
            .addOnCompleteListener {
                livedataAuthProvider.value = AppState.Loading(AUTH_SECCES_OPEN_NEXT_SCREEN)
            }
            .addOnFailureListener {
                livedataAuthProvider.value = AppState.Error(it.message.toString())
            }
    }


}