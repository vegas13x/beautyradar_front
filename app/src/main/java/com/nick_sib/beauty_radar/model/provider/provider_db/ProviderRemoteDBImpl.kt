package com.nick_sib.beauty_radar.model.provider.provider_db

import com.nick_sib.beauty_radar.model.provider.api.ApiService
import com.nick_sib.beauty_radar.model.provider.repository.user.UserResponse
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO

class ProviderRemoteDBImpl(private val api: ApiService) : IProviderRemoteDB {

    override suspend fun createUser(UserDTO: UserDTO): UserResponse {
        return api.createUserAsync(UserDTO)
    }

    override suspend fun updateUser(UserDTO: UserDTO): UserResponse {
        return api.updateUserAsync(UserDTO)
    }

    override suspend fun getUserByUPN(upn: String): UserResponse {
        return api.getUserByUPNAsync(upn)
    }

    override suspend fun getUserList(): List<UserResponse> {
        return api.getUserListAsync()
    }

    override suspend fun deleteUser(upn: String): UserResponse {
        return api.deleteUserAsync(upn)

    }
}