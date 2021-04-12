package com.nick_sib.beauty_radar.data.error

import com.nick_sib.beauty_radar.data.state.AppState

class ToastError(message: String): Throwable(message)
class HintError(message: String?): Throwable(message)