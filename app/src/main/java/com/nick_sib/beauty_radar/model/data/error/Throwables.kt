package com.nick_sib.beauty_radar.model.data.error

class ToastError(message: String): Throwable(message)
class HintError(message: String?): Throwable(message)