package com.nick_sib.beauty_radar.view_model.interactor.impl

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.view_model.interactor.core.ProfileInfoInteractor

class ProfileInfoInteractorImpl(private val remoteRepo: RemoteRepository<AppState>): ProfileInfoInteractor<AppState> {}