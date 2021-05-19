package com.nick_sib.beauty_radar.view_model.interactor.impl

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.view_model.interactor.core.MasterClientInteractor

class MasterClientInteractorImpl(private val remoteRepo: RemoteRepository<AppState>) :
    MasterClientInteractor<AppState> {

    override fun getData(): AppState =
        remoteRepo.getCalendar()

}