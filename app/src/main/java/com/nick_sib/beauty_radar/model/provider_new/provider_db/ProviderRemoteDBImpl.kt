package com.nick_sib.beauty_radar.model.provider_new.provider_db

import com.nick_sib.beauty_radar.model.provider_new.api.ApiService
import com.nick_sib.beauty_radar.model.provider_new.repository.user.NewUserProfile
import com.nick_sib.beauty_radar.model.provider_new.repository.user.User

class ProviderRemoteDBImpl(private val api: ApiService) : IProviderRemoteDB {

    override suspend fun createUser(user: User): NewUserProfile {
        return api.createUserAsync(user).await()
    }

    override suspend fun updateUser(user: User): NewUserProfile {
        return api.updateUserAsync(user).await()
    }


    override suspend fun getUserByUPN(upn: String): NewUserProfile {
        return api.getUserByUPNAsync(upn).await()
    }

    override suspend fun getUserList(): List<NewUserProfile> {
        return api.getUserListAsync().await()
    }

    override suspend fun deleteUser(upn: String): NewUserProfile {
        return api.deleteUserAsync(upn).await()

    }
}