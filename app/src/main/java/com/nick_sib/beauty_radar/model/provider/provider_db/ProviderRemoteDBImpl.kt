package com.nick_sib.beauty_radar.model.provider.provider_db

import com.nick_sib.beauty_radar.model.data.entites.UserMaster
import com.nick_sib.beauty_radar.model.provider.api.ApiService
import com.nick_sib.beauty_radar.model.provider.repository.user.MasterDTO
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDBCheck
import com.nick_sib.beauty_radar.model.provider.repository.user.UserResponse
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO

class ProviderRemoteDBImpl(private val api: ApiService) : IProviderRemoteDB {

    override suspend fun createUser(UserDTO: UserDTO): UserResponse =
        api.createUserAsync(UserDTO)

    override suspend fun updateUser(id: Long?,userDTO: UserDTO): UserResponse =
        api.updateUserAsync(id,userDTO)

    override suspend fun getUserByUPN(upn: String): UserResponse =
        api.getUserByUPNAsync(upn)

    override suspend fun existUserByUPN(upn: String): UserDBCheck =
        api.existUserByUPNAsync(upn)

//    override suspend fun createMaster(masterDTO: MasterDTO): UserResponse =
//        api.createMasterAsync(masterDTO)


//    override suspend fun getUserList(): List<UserResponse> =
//        api.getUserListAsync()
//
//    override suspend fun deleteUser(upn: String): UserResponse =
//        api.deleteUserAsync(upn)

}