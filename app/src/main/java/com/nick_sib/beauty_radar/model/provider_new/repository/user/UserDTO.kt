package com.nick_sib.beauty_radar.model.provider_new.repository.user

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("email") var email: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("img") var img: String? = null,
    @SerializedName("login") var login: String? = null,
    @SerializedName("master") var masterDTO: MasterDTO? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("rating") var rating: Int? = null,
    @SerializedName("upn") var upn: String? = null
)

