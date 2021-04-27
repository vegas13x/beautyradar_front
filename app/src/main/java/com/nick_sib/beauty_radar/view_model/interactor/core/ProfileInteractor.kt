package com.nick_sib.beauty_radar.view_model.interactor.core

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider_new.repository.user.User

interface ProfileInteractor<S> : Interactor {
    suspend fun getUserByUPNFromDB(uid: String): AppState
    suspend fun updateUser(user: User): AppState
}