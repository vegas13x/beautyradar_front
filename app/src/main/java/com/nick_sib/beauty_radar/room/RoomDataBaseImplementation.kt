package com.nick_sib.beauty_radar.room

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myapplication.HistoryDao
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.model.entity.room.RoomMaster


class RoomDataBaseImplementation(private val historyDao: HistoryDao): IRoomSource{

    override fun getData(): List<RoomMaster> {
        var rm = RoomMaster("1","1","1","1")
        historyDao.insert(rm)
        Log.d("11111", "getData: " + historyDao.getAll())
        return historyDao.getAll()
    }

}
