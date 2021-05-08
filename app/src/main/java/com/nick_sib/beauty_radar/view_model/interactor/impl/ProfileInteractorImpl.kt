package com.nick_sib.beauty_radar.view_model.interactor.impl

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.view_model.interactor.core.ProfileInteractor

class ProfileInteractorImpl(private val remoteRepo: RemoteRepository<AppState>) :ProfileInteractor<AppState> {

    override suspend fun existUserByUPNFromDB(uid: String): AppState =
        remoteRepo.existUserByUPNFromDB(uid)

    override suspend fun updateUser(id: String): AppState =
        remoteRepo.updateUser(id)

}