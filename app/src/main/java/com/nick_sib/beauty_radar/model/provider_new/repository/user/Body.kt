package com.nick_sib.beauty_radar.model.provider_new.repository.user

import com.google.gson.annotations.SerializedName

data class Body (

    @SerializedName("id") val id : Int,
    @SerializedName("upn") val upn : String,
    @SerializedName("login") val login : Int,
    @SerializedName("master") val master : String,
    @SerializedName("name") val name : String,
    @SerializedName("phone") val phone : String,
    @SerializedName("email") val email : String,
    @SerializedName("img") val img : String,
    @SerializedName("rating") val rating : String
)