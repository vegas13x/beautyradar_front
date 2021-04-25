package com.nick_sib.beauty_radar.model.room

import android.util.Log

class RoomDataBaseImplementation(private val historyDao: HistoryDao): IRoomSource{

    override fun getData(): List<RoomMaster> {
        var rm = RoomMaster("1","1","1","1")
        historyDao.insert(rm)
        Log.d("11111", "getData: " + historyDao.getAll())
        return historyDao.getAll()
    }

}
