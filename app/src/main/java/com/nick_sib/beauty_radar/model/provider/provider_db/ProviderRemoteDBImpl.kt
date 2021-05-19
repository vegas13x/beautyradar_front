package com.nick_sib.beauty_radar.model.provider.provider_db

import com.nick_sib.beauty_radar.model.provider.api.ApiService
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDBCheck
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.model.provider.repository.user.UserResponse

class ProviderRemoteDBImpl(private val api: ApiService) : IProviderRemoteDB {

    override suspend fun createUser(UserDTO: UserDTO): UserResponse =
        api.createUserAsync(UserDTO)

    override suspend fun updateUser(id: Long?,userDTO: UserDTO): UserResponse =
        api.updateUserAsync(id,userDTO)

    override suspend fun getUserByUPN(upn: String): UserResponse =
        api.getUserByUPNAsync(upn)

    override suspend fun existUserByUPN(upn: String): UserDBCheck =
        api.existUserByUPNAsync(upn)
}