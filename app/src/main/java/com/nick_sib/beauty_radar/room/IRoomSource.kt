package com.nick_sib.beauty_radar.room

import com.nick_sib.beauty_radar.model.entity.room.RoomMaster


interface IRoomSource {
    fun getData(): List<RoomMaster>
}