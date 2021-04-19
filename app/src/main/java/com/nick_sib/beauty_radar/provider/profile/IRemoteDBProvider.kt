package com.nick_sib.beauty_radar.provider.profile

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.profile.entities.CalendareProfile
import com.nick_sib.beauty_radar.provider.profile.entities.UserProfile

interface IRemoteDBProvider {

    fun getLiveDataProfileProvider(): LiveData<AppState>

    fun checkUserInDdByUID(uid: String)
    fun createUserInDb(user: UserProfile)
    fun getUserFromDbByUID(uid: String)
    fun getUsersFromDb()
    fun createCalendarDateInDb(calendar: CalendareProfile)
    fun getCalendarDateFromDb(uid: String)
    fun clearLivedata()

    suspend fun getUser(uid: String): AppState
    fun createUIDUser(user: UserProfile)
    fun getListUsers(list: ArrayList<UserProfile>)
    fun getCalendarDate(uid: String)
}