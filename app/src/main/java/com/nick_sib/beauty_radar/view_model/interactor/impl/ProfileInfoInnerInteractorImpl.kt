package com.nick_sib.beauty_radar.view_model.interactor.impl

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.view_model.interactor.core.ProfileInfoInnerInteractor

class ProfileInfoInnerInteractorImpl(private val remoteRepo: RemoteRepository<AppState>):
    ProfileInfoInnerInteractor<AppState> {
    override suspend fun getUserFromDB(): AppState =
        remoteRepo.getUserFromDB()
}