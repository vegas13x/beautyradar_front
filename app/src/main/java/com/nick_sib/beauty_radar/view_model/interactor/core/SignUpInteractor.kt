package com.nick_sib.beauty_radar.view_model.interactor.core

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO

interface SignUpInteractor<S> : Interactor {
    suspend fun createUser(UserDTO: UserDTO): AppState

    suspend fun updateUser(UserDTO: Long?, userDTO: UserDTO): AppState

    suspend fun getToken(): String
}