package com.nick_sib.beauty_radar.model.provider.auth

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.nick_sib.beauty_radar.SingletonUID
import com.nick_sib.beauty_radar.model.data.entites.UserMaster
import com.nick_sib.beauty_radar.model.data.error.ToastError
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view.utils.CODE_RECEIVED_VISIBLE_ENTER_CODE_FRAGMENT
import com.nick_sib.beauty_radar.view.utils.USER_SIGNOUT
import java.util.concurrent.TimeUnit
import kotlin.coroutines.Continuation
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
class AuthProviderImpl(private val authUser: FirebaseAuth) : IAuthProvider{

    private lateinit var localVerificationId: String
    private var resendingToken: PhoneAuthProvider.ForceResendingToken? = null
    private var resendingPhone: String? = null

    /**
     *Старт регистрации по телефону: создаем настройки для кода - отправляем на сервер ,
     * ждём ответ и обрабатывает посредством callback для аутентификатора телефона
     */
    override suspend fun startPhoneNumberVerification(activity: Activity, phone: String): AppState {
        resendingPhone = phone
        return suspendCoroutine { res ->
            val mcallbacks  = callback(res)
            val options = PhoneAuthOptions.newBuilder(authUser)
                .setPhoneNumber(phone)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(activity)                 // Activity (for callback binding)
                .setCallbacks(mcallbacks)          // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }

    /**
     * Повторный запрос кода с использование полученого токена от первичного запроса
     */
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override suspend fun resentVerificationCode(activity: Activity): AppState {
        return suspendCoroutine {
            val mCallback = callback(it)
            val options = PhoneAuthOptions.newBuilder(authUser)
                .setPhoneNumber(resendingPhone)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(activity) // Activity (for callback binding)
                .setForceResendingToken(resendingToken)
                .setCallbacks(mCallback)          // OnVerificationStateChangedCallbacks
                .build()

            PhoneAuthProvider.verifyPhoneNumber(options)

        }
    }

    private fun callback(res: Continuation<AppState>): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
        val mcallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                res.resume(
                        AppState.Success<UserMaster>(
                            UserMaster(
                                "testName",
                                "testEmail",
                                uid = authUser.uid
                            )
                        )
                    )
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                res.resume(AppState.Error(ToastError(exception.message.toString())))
            }

            override fun onCodeSent(
                verifyID: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verifyID, token)
                localVerificationId = verifyID
                resendingToken = token
                res.resume(AppState.Success(CODE_RECEIVED_VISIBLE_ENTER_CODE_FRAGMENT))
            }
        }
        return mcallbacks
    }


    override suspend fun verifyPhoneNumber(code: String): AppState {
        val credential: PhoneAuthCredential =
            PhoneAuthProvider.getCredential(localVerificationId, code)
        return signInWithPhoneAuthCredential(credential)

    }


    /**
     * Функция выхода из учетной записи
     */
    override suspend fun signOut():AppState {
        return suspendCoroutine {
            authUser.signOut()
            it.resume(AppState.Success(USER_SIGNOUT))
        }
    }


    private suspend fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential
    ): AppState {
        return suspendCoroutine { res ->
            authUser.signInWithCredential(credential)
                .addOnFailureListener {
                    res.resume(AppState.Error(ToastError(it.message.toString())))
                }
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        SingletonUID.setUID(authUser.uid)
                        res.resume(
                            AppState.Success(
                                UserMaster(
                                    "testName",
                                    "testEmail",
                                    uid = authUser.uid
                                )
                            )
                        )
                    }
                }
        }
    }
}
