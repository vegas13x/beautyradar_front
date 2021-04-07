package com.nick_sib.beauty_radar.provider.auth_

import android.app.Activity
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.entites.UserMaster
import com.nick_sib.beauty_radar.data.state.AppState
/**
 * @author Alex Volkov(Volkos)
 *Интерфейс для взаимодействия с классом авторизации/регистрации
 */

interface IAuthProvider {
    fun getLiveDataAuthProvider(): LiveData<AppState>
    fun singUpEmailAndPasswordUser(email: String, password: String)
    fun startPhoneNumberVerification(activity: Activity, phone: String)
    fun resentVerificationCode(activity: Activity, phone: String)
    fun verifyPhoneNumber(code: String)
    fun addEmailAndPasswordInCurrentUser(email: String, password: String)
    fun signOut()
    fun signInEmailPassword(email: String,password: String)
}