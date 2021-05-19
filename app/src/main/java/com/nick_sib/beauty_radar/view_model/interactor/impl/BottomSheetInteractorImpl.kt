package com.nick_sib.beauty_radar.view_model.interactor.impl

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.view_model.interactor.core.BottomSheetInteractor

class BottomSheetInteractorImpl(private val repository: RemoteRepository<AppState>)
    : BottomSheetInteractor<AppState> {

    override suspend fun getService(): AppState = repository.getService()

}