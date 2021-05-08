package com.nick_sib.beauty_radar.model.provider.provider_db

import com.nick_sib.beauty_radar.model.provider.repository.user.UserDBCheck
import com.nick_sib.beauty_radar.model.provider.repository.user.UserResponse
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO

interface IProviderRemoteDB {

    suspend fun createUser(UserDTO: UserDTO): UserResponse
    suspend fun updateUser(id: Int?): UserResponse
    suspend fun getUserByUPN(id: String): UserResponse
    suspend fun existUserByUPN(upn: String): UserDBCheck

//    suspend fun getUserList():List<UserResponse>
//    suspend fun deleteUser(upn: String): UserResponse
}