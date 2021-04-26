package com.nick_sib.beauty_radar.model.repository.core

interface RemoteRepository<S> : Repository<S> {
    suspend fun checkUserInDB(uid: String): S
}