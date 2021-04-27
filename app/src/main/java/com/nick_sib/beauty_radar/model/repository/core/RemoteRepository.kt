package com.nick_sib.beauty_radar.model.repository.core

import com.nick_sib.beauty_radar.model.provider_new.repository.user.User

interface RemoteRepository<S> : Repository<S> {

    suspend fun createUser(user: User): S
    suspend fun updateUser(user: User): S

    suspend fun getUserByUPNFromDB(uid: String): S

    suspend fun getUserList(): S
    suspend fun deleteUser(uid: String): S
}