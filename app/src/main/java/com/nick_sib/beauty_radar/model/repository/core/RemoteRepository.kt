package com.nick_sib.beauty_radar.model.repository.core

import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO

interface RemoteRepository<S> : Repository<S> {

    suspend fun createUser(UserDTO: UserDTO): S
    suspend fun updateUser(UserDTO: UserDTO): S

    suspend fun getUserByUPNFromDB(uid: String): S

    suspend fun getUserList(): S
    suspend fun deleteUser(uid: String): S
}