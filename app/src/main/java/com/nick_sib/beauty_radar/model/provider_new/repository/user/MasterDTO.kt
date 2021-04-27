package com.nick_sib.beauty_radar.model.provider_new.repository.user

import com.google.gson.annotations.SerializedName

data class MasterDTO(
    @SerializedName("address") var address : String? = null,
    @SerializedName("id") var id : Int? = null,
    @SerializedName("rating") var rating : Int? = null,
)