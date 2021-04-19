package com.nick_sib.beauty_radar.data.state
/**
 * @author Alex Volkov(Volkos)
 *
 * Класс состояний приложения
 */
sealed class AppState {
    data class Success<out T>(val data: T) : AppState()
    data class SystemMessage(val message: String) : AppState()
    data class Loading(val progress: Int) : AppState()
    data class Error(val error: Throwable) : AppState()
    class Empty: AppState()
}