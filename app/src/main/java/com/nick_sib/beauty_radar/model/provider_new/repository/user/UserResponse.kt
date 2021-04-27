package com.nick_sib.beauty_radar.model.provider_new.repository.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("body") val body: UserDTO? = null,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
)