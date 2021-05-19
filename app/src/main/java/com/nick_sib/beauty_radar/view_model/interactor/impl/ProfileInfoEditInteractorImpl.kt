package com.nick_sib.beauty_radar.view_model.interactor.impl

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.master.UserMasterProfile
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.view_model.interactor.core.ProfileInfoEditInteractor

class ProfileInfoEditInteractorImpl(private val remoteRepo: RemoteRepository<AppState>): ProfileInfoEditInteractor<AppState> {

    override suspend fun sendUserToDB(userMasterProfile: UserMasterProfile): AppState =
        remoteRepo.sendUserToDB(userMasterProfile)

    override suspend fun getUserFromDB(): AppState =
        remoteRepo.getUserFromDB()


}