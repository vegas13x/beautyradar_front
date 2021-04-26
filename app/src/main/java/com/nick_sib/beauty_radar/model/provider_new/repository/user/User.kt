package com.nick_sib.beauty_radar.model.provider_new.repository.user

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email") var email : String,
    @SerializedName("id") var id : Int,
    @SerializedName("img") var img : String,
    @SerializedName("login") var login : String,
    @SerializedName("master") var master : Master,
    @SerializedName("name") var name : String,
    @SerializedName("phone") var phone : String,
    @SerializedName("rating") var rating : Int,
    @SerializedName("upn") var upn : String)

