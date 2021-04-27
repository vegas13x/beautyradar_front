package com.nick_sib.beauty_radar.model.provider_new.repository.user

import com.squareup.moshi.Json

data class UserResponse (
    @field:Json(name = "body") val body: UserDTO? = null,
    @field:Json(name = "code") val code: Int,
    @field:Json(name = "message") val message: String,
)