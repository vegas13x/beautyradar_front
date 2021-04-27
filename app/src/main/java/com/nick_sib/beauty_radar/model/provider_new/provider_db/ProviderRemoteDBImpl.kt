package com.nick_sib.beauty_radar.model.provider_new.provider_db

import com.nick_sib.beauty_radar.model.provider_new.api.ApiService
import com.nick_sib.beauty_radar.model.provider_new.repository.user.UserResponse
import com.nick_sib.beauty_radar.model.provider_new.repository.user.UserDTO

class ProviderRemoteDBImpl(private val api: ApiService) : IProviderRemoteDB {

    override suspend fun createUser(UserDTO: UserDTO): UserResponse {
        return api.createUserAsync(UserDTO).await()
    }

    override suspend fun updateUser(UserDTO: UserDTO): UserResponse {
        return api.updateUserAsync(UserDTO).await()
    }


    override suspend fun getUserByUPN(upn: String): UserResponse {
        return api.getUserByUPNAsync(upn).await()
    }

    override suspend fun getUserList(): List<UserResponse> {
        return api.getUserListAsync().await()
    }

    override suspend fun deleteUser(upn: String): UserResponse {
        return api.deleteUserAsync(upn).await()

    }
}