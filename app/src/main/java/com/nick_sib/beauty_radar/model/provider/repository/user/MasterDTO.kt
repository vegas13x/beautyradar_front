package com.nick_sib.beauty_radar.model.provider.repository.user

import com.squareup.moshi.Json

data class MasterDTO(
    @field:Json(name = "id") var adress: String? = null,
    @field:Json(name = "upn") var id: Long? = null,
    @field:Json(name = "token") var latitude: Long? = null,
    @field:Json(name = "login") var longitude: Long? = null,
    @field:Json(name = "name") var rating: Long? = null,
    @field:Json(name = "phone") var userId: Long? = null
)