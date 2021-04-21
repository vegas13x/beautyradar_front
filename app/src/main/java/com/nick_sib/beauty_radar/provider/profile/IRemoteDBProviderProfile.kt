package com.nick_sib.beauty_radar.provider.profile

import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.profile.entities.UserProfile

interface IRemoteDBProviderProfile {

    suspend fun checkUserInDdByUID(uid: String): AppState

    fun createUserInDb(user: UserProfile)
    suspend fun getUserFromDbByUID(uid: String): AppState
    suspend fun getUsersFromDb(): AppState

}