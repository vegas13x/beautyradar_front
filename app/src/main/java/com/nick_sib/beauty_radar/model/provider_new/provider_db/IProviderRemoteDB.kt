package com.nick_sib.beauty_radar.model.provider_new.provider_db

import com.nick_sib.beauty_radar.model.provider_new.repository.user.NewUserProfile
import com.nick_sib.beauty_radar.model.provider_new.repository.user.User

interface IProviderRemoteDB {

    fun createUser(user: User)
    fun updateUser(user: User)

    suspend fun getUserByUPN(upn: String): NewUserProfile
    suspend fun getUserList():List<NewUserProfile>
    suspend fun deleteUser(upn: String): NewUserProfile
}