package com.nick_sib.beauty_radar.model.provider_new.repository.user

import com.google.gson.annotations.SerializedName

data class Master(
    @SerializedName("address") var address : String,
    @SerializedName("id") var id : Int,
    @SerializedName("rating") var rating : Int
)