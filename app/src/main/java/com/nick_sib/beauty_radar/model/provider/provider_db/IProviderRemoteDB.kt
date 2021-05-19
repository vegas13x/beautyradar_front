package com.nick_sib.beauty_radar.model.provider.provider_db

import com.nick_sib.beauty_radar.model.provider.repository.user.UserDBCheck
import com.nick_sib.beauty_radar.model.provider.repository.user.UserResponse
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO

interface IProviderRemoteDB {

    suspend fun createUser(UserDTO: UserDTO): UserResponse
    suspend fun updateUser(id: Long?, userDTO: UserDTO): UserResponse
    suspend fun getUserByUPN(upn: String): UserResponse
    suspend fun existUserByUPN(upn: String): UserDBCheck
}