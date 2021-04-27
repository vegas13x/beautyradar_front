package com.nick_sib.beauty_radar.view_model.interactor.core

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider_new.repository.user.User

interface SignUpInteractor<S> : Interactor {
    suspend fun createUser(user: User): AppState
}