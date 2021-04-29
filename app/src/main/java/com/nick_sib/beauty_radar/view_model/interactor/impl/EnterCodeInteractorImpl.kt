package com.nick_sib.beauty_radar.view_model.interactor.impl

import android.util.Log
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.view.utils.TAG_DEBAG
import com.nick_sib.beauty_radar.view.utils.USER_IS_DISABLE_IN_DB
import com.nick_sib.beauty_radar.view.utils.USER_IS_ENABLE_IN_DB
import com.nick_sib.beauty_radar.view_model.interactor.core.EnterCodeInteractor

class EnterCodeInteractorImpl(private val remoteRepo: RemoteRepository<AppState>) :
    EnterCodeInteractor<AppState> {

    override suspend fun getUserByUPNFromDB(uid: String): AppState {
        Log.d(TAG_DEBAG, "getUserByUPNFromDB1: ")
        return remoteRepo.getUserByUPNFromDB(uid)}

}

