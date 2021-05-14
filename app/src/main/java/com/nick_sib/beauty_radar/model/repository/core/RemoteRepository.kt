package com.nick_sib.beauty_radar.model.repository.core

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO

interface RemoteRepository<S> : Repository<S> {

    suspend fun createUser(UserDTO: UserDTO): S
    suspend fun updateUser(id: Long?,userDTO: UserDTO): S
    suspend fun existUserByUPNFromDB(uid: String): S
    suspend fun getUserByUPNFromDB(uid: String): AppState
}