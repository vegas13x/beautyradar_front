package com.nick_sib.beauty_radar.model.provider_new.provider_db

import com.nick_sib.beauty_radar.model.provider_new.repository.user.NewUserProfile
import com.nick_sib.beauty_radar.model.provider_new.repository.user.User

interface IProviderRemoteDB {

    suspend fun createUser(user: User): NewUserProfile
    suspend fun updateUser(user: User): NewUserProfile

    suspend fun getUserByUPN(upn: String): NewUserProfile
    suspend fun getUserList():List<NewUserProfile>
    suspend fun deleteUser(upn: String): NewUserProfile
}