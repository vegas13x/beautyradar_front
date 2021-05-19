package com.nick_sib.beauty_radar.model.provider.repository.master

import com.squareup.moshi.Json

class UserMasterProfile (
        @field:Json(name = "name") var name: String? = null,
        @field:Json(name = "surname") var surname: String? = null,
        @field:Json(name = "address") var address: String? = null,
        @field:Json(name = "phone") var phone: String? = null,
        @field:Json(name = "date_birthday") var dateBirthday: String? = null,
        @field:Json(name = "about_urself") var aboutUrself: String? = null
)