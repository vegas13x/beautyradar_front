package com.nick_sib.beauty_radar.model.provider.repository.user

import com.squareup.moshi.Json

data class UserDBCheck(

    @field:Json(name = "body") val body : Boolean? = null,
    @field:Json(name = "code") val code: Int? = null,
    @field:Json(name = "message") val message: String? = null)
