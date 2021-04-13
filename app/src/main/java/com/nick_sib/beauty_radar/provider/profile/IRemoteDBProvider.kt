package com.nick_sib.beauty_radar.provider.profile

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.profile.entities.UserProfile

interface IRemoteDBProvider {

    fun getLiveDataProfileProvider(): LiveData<AppState>

    fun getUser(uid: String)
    fun createUIDUser(user: UserProfile)
    fun getListUsers(list: ArrayList<UserProfile>)
    fun getCalendarDate(uid: String)
}