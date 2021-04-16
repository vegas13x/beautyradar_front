package com.nick_sib.beauty_radar.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomMaster(
    @PrimaryKey var id: String,
    var uid: String,
    var avatarUrl: String,
    var reposUrl: String

)
