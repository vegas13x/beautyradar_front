package com.nick_sib.beauty_radar.model.provider_new.repository.user

import com.google.gson.annotations.SerializedName

data class NewUserProfile (
    @SerializedName("body") var body : User,
    @SerializedName("code") var code : Int,
    @SerializedName("message") var message : String
)