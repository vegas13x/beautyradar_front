package com.nick_sib.beauty_radar.model.provider_new.repository.user

import com.squareup.moshi.Json

data class MasterDTO(
    @field:Json(name = "address") var address : String? = null,
    @field:Json(name = "id") var id : Int? = null,
    @field:Json(name = "rating") var rating : Int? = null,
)