package com.nick_sib.beauty_radar.provider.auth_

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.messaging.FirebaseMessaging
import com.nick_sib.beauty_radar.data.entites.UserMaster
import com.nick_sib.beauty_radar.data.error.ToastError
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.ui.utils.*
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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
    private val livedataAuthProvider: MutableLiveData<AppState> = MutableLiveData()
    private lateinit var localVerificationId: String
    private var resendingToken: PhoneAuthProvider.ForceResendingToken? = null
    private var resendingPhone: String? = null


    private val currentUser
        get() = authUser.currentUser

    /**
     * Метод подписки на локальную liveData провайдера(класса)
     */
    override fun getLiveDataAuthProvider(): LiveData<AppState> {
        return livedataAuthProvider
    }


    /**
     *Старт регистрации по телефону: создаем настройки для кода - отправляем на сервер ,
     * ждём ответ и обрабатывает посредством callback для аутентификатора телефона
     */


    override suspend fun startPhoneNumberVerification(activity: Activity, phone: String): AppState {
        return suspendCoroutine {res ->
            val mcallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    signInWithPhoneAuthCredential(credential)
                    res.resume(AppState.Error(ToastError("")))
                }
                override fun onVerificationFailed(exception: FirebaseException) {
                    res.resume(AppState.Error(ToastError(exception.message.toString())))
                }

                override fun onCodeSent(verifyID: String, token: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(verifyID, token)
                    localVerificationId = verifyID
                    resendingToken = token
                    resendingPhone = phone
                    res.resume(AppState.Success(CODE_RECEIVED_VISIBLE_ENTER_CODE_FRAGMENT))
                }
            }
            val options = PhoneAuthOptions.newBuilder(authUser)
                .setPhoneNumber(phone)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(activity)                 // Activity (for callback binding)
                .setCallbacks(mcallbacks)          // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
//            Log.d("myLOG", "startPhoneNumberVerification: ")
        }
    }

    /**
     * Повторный запрос кода с использование полученого токена от первичного запроса
     */
    override suspend fun resentVerificationCode(activity: Activity/*, phone: String*/): AppState {
        if (resendingPhone == null || resendingToken == null) {
            return AppState.Error(ToastError("USER token not inited yet"))
        } else {
            return suspendCoroutine {res ->
                val mcallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                        res.resume(AppState.Error(ToastError("")))
                    }
                    override fun onVerificationFailed(exception: FirebaseException) {
                        res.resume(AppState.Error(ToastError(exception.message.toString())))
                    }
                    override fun onCodeSent(
                        verifyID: String,
                        token: PhoneAuthProvider.ForceResendingToken
                    ) {
                    super.onCodeSent(verifyID, token)
                        res.resume(AppState.Success(CODE_RECEIVED_VISIBLE_ENTER_CODE_FRAGMENT))
                    }
                }
                val options = PhoneAuthOptions.newBuilder(authUser)
                    .setPhoneNumber(resendingPhone ?: "")       // Phone number to verify
                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                    .setActivity(activity) // Activity (for callback binding)
                    .setForceResendingToken(resendingToken!!)
                    .setCallbacks(mcallbacks)          // OnVerificationStateChangedCallbacks
                    .build()
                PhoneAuthProvider.verifyPhoneNumber(options)
            }
        }
    }


    /**
     * Подтверждение кода
     */
    override suspend fun verifyPhoneNumber(code: String): AppState {
        val credential: PhoneAuthCredential =
            PhoneAuthProvider.getCredential(localVerificationId, code)
        return suspendCoroutine {res ->
            authUser.signInWithCredential(credential)
                .addOnFailureListener {
                    res.resume(AppState.Error(ToastError(it.message.toString())))
                }
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        res.resume(AppState.Success(UserMaster("testName","testEmail",uid = authUser.uid)))
                    }
                }
        }
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
                    livedataAuthProvider.value =
                        AppState.Error(ToastError("Данные пользователя не обновлены!"))
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
                        AppState.Error(ToastError("Пользователя не существует или неверно введены данные"))
                }
            }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        authUser.signInWithCredential(credential)
            .addOnFailureListener {
                livedataAuthProvider.value = AppState.Error(ToastError(it.message.toString()))
            }
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    livedataAuthProvider.value = AppState.Success(UserMaster("testName","testEmail",uid = authUser.uid))
                }
            }
    }


}
