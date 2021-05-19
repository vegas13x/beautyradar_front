package com.nick_sib.beauty_radar.view_model.interactor.core

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.master.UserMasterProfile

interface ProfileInfoInnerInteractor<S>: Interactor  {

    suspend fun getUserFromDB(): S


}