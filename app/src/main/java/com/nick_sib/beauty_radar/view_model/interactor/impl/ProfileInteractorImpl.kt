package com.nick_sib.beauty_radar.view_model.interactor.impl

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider_new.repository.user.UserDTO
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.view_model.interactor.core.ProfileInteractor

class ProfileInteractorImpl(private val remoteRepo: RemoteRepository<AppState>) :ProfileInteractor<AppState> {

    override suspend fun getUserByUPNFromDB(uid: String): AppState {
        return remoteRepo.getUserByUPNFromDB(uid)
    }

    override suspend fun updateUser(UserDTO: UserDTO): AppState {
        return remoteRepo.updateUser(UserDTO)
    }
}