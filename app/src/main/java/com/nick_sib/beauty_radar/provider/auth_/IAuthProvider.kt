package com.nick_sib.beauty_radar.provider.auth_

import android.app.Activity
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseUser
import com.nick_sib.beauty_radar.data.state.AppState
import kotlinx.coroutines.CoroutineScope

/**
 * @author Alex Volkov(Volkos)
 *Интерфейс для взаимодействия с классом авторизации/регистрации
 */

interface IAuthProvider {
    fun getLiveDataAuthProvider(): LiveData<AppState>
    suspend fun resentVerificationCode(activity: Activity): AppState
    suspend fun verifyPhoneNumber(code: String): AppState
    fun addEmailAndPasswordInCurrentUser(email: String, password: String)
    fun signOut()
    suspend fun startPhoneNumberVerification(activity: Activity, phone: String): AppState
    fun signInEmailPassword(email: String, password: String)
}

//    fun singUpEmailAndPasswordUser(email: String, password: String)