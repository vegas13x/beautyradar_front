package com.nick_sib.beauty_radar.data.state

sealed class AppState {
    data class Success<out T>(val data: T) : AppState()
    data class SystemMessage(val message: String) : AppState()
    data class Loading(val progress: Int) : AppState()
    data class Error(val error: String) : AppState()
}