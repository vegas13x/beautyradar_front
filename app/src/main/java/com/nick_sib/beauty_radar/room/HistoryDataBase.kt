package com.nick_sib.beauty_radar.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.HistoryDao
import com.nick_sib.beauty_radar.model.entity.room.RoomMaster

@Database(entities = [RoomMaster::class], version = 1, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}
