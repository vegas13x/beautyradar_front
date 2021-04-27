package com.nick_sib.beauty_radar.view_model.interactor.core

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider_new.repository.user.UserDTO

interface SignUpInteractor<S> : Interactor {
    suspend fun createUser(UserDTO: UserDTO): AppState
}