package com.nick_sib.beauty_radar.model.provider_new.repository.user

import com.google.gson.annotations.SerializedName


data class NewUserProfile (
    @SerializedName("body") val body: User,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
)