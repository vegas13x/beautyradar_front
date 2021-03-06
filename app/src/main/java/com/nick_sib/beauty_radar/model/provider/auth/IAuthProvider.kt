package com.nick_sib.beauty_radar.model.provider.auth

import android.app.Activity
import com.nick_sib.beauty_radar.model.data.state.AppState

/**
 * @author Alex Volkov(Volkos)
 *Интерфейс для взаимодействия с классом авторизации/регистрации
 */

interface IAuthProvider {
    suspend fun signOut():AppState
    suspend fun startPhoneNumberVerification(activity: Activity, phone: String): AppState
    suspend fun resentVerificationCode(activity: Activity): AppState
    suspend fun verifyPhoneNumber(code: String): AppState

}