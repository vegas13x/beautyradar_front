package com.nick_sib.beauty_radar.model.provider.provider_db

import com.nick_sib.beauty_radar.model.provider.api.ApiService
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDBCheck
import com.nick_sib.beauty_radar.model.provider.repository.user.UserResponse
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO

class ProviderRemoteDBImpl(private val api: ApiService) : IProviderRemoteDB {

    override suspend fun createUser(UserDTO: UserDTO): UserResponse =
        api.createUserAsync(UserDTO)

    override suspend fun updateUser(id: String): UserResponse =
        api.updateUserAsync(id)

    override suspend fun getUserByUPN(id: String): UserResponse =
        api.getUserByUPNAsync(id)

    override suspend fun existUserByUPN(upn: String): UserDBCheck =
        api.existUserByUPNAsync(upn)


//    override suspend fun getUserList(): List<UserResponse> =
//        api.getUserListAsync()
//
//    override suspend fun deleteUser(upn: String): UserResponse =
//        api.deleteUserAsync(upn)

}