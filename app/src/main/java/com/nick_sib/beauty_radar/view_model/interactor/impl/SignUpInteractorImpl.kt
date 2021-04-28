package com.nick_sib.beauty_radar.view_model.interactor.impl

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.view_model.interactor.core.SignUpInteractor

class SignUpInteractorImpl(private val remoteRepo: RemoteRepository<AppState>) :SignUpInteractor<AppState> {

    override suspend fun createUser(UserDTO: UserDTO): AppState {
        return remoteRepo.createUser(UserDTO)
    }
}