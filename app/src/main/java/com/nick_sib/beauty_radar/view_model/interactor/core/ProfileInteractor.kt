package com.nick_sib.beauty_radar.view_model.interactor.core

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider_new.repository.user.UserDTO

interface ProfileInteractor<S> : Interactor {
    suspend fun getUserByUPNFromDB(uid: String): AppState
    suspend fun updateUser(UserDTO: UserDTO): AppState
}