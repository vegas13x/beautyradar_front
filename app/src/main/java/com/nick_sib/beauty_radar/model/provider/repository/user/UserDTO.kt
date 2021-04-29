package com.nick_sib.beauty_radar.model.provider.repository.user

import com.squareup.moshi.Json

data class UserDTO(
    @field:Json(name = "email") var email: String? = null,
    @field:Json(name = "id") var id: Int? = null,
    @field:Json(name = "img") var img: String? = null,
    @field:Json(name = "login") var login: String? = null,
    @field:Json(name = "master") var masterDTO: MasterDTO? = null,
    @field:Json(name = "name") var name: String? = null,
    @field:Json(name = "phone") var phone: String? = null,
    @field:Json(name = "rating") var rating: Int? = null,
    @field:Json(name = "upn") var upn: String? = null,
    @field:Json(name = "token") var token: String? = null
)

