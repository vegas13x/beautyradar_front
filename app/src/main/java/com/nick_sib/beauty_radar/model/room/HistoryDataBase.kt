package com.nick_sib.beauty_radar.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomMaster::class], version = 1, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}
