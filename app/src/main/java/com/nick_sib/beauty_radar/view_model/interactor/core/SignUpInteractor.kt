package com.nick_sib.beauty_radar.view_model.interactor.core

import com.google.firebase.inject.Deferred
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO

interface SignUpInteractor<S> : Interactor {
    suspend fun createUser(UserDTO: UserDTO): AppState

    suspend fun updateUser(UserDTO: Int): AppState

    suspend fun getToken(): String
}