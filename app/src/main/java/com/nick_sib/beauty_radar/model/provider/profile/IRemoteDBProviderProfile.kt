package com.nick_sib.beauty_radar.model.provider.profile

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.profile.entities.UserProfile

interface IRemoteDBProviderProfile {

    suspend fun checkUserInDdByUID(uid: String): AppState

    fun createUserInDb(user: UserProfile)
    suspend fun getUserFromDbByUID(uid: String): AppState
    suspend fun getUsersFromDb(): AppState

}