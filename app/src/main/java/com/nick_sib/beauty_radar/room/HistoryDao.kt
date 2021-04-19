package com.example.myapplication

import androidx.room.*
import com.nick_sib.beauty_radar.model.entity.room.RoomMaster

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomMaster)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomMaster)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomMaster>)

    @Update
    fun update(user: RoomMaster)

    @Update
    fun update(vararg users: RoomMaster)

    @Update
    fun update(users: List<RoomMaster>)

    @Delete
    fun delete(user: RoomMaster)

    @Delete
    fun delete(vararg users: RoomMaster)

    @Delete
    fun delete(users: List<RoomMaster>)

    @Query("SELECT * FROM RoomMaster")
    fun getAll(): List<RoomMaster>

    @Query("SELECT * FROM RoomMaster WHERE uid = :uid LIMIT 1")
    fun findByUID(uid: String): RoomMaster?

    @Query("SELECT * FROM RoomMaster WHERE id = 1")
    fun findFirst(): RoomMaster?


}
