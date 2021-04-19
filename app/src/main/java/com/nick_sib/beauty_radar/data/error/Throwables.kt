package com.nick_sib.beauty_radar.data.error

class ToastError(message: String): Throwable(message)
class HintError(message: String?): Throwable(message)