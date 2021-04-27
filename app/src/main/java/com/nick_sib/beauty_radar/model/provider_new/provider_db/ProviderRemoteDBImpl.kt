package com.nick_sib.beauty_radar.model.provider_new.provider_db

import com.nick_sib.beauty_radar.model.provider_new.api.ApiService
import com.nick_sib.beauty_radar.model.provider_new.repository.user.NewUserProfile
import com.nick_sib.beauty_radar.model.provider_new.repository.user.User

class ProviderRemoteDBImpl(private val api: ApiService) : IProviderRemoteDB {

    override fun createUser(user: User) {

    }

    override fun updateUser(user: User) {

    }

    override suspend fun getUserByUPN(upn: String): NewUserProfile {
        return api.getUserByUPN(upn).await()
    }

    override suspend fun getUserList(): List<NewUserProfile> {
        return api.getUserList().await()
    }

    override suspend fun deleteUser(upn: String): NewUserProfile {
        return api.deleteUser(upn).await()

    }
}