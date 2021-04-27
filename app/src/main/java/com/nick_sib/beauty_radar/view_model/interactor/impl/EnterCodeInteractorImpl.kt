package com.nick_sib.beauty_radar.view_model.interactor.impl

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.view.utils.USER_IS_DISABLE_IN_DB
import com.nick_sib.beauty_radar.view_model.interactor.core.EnterCodeInteractor

class EnterCodeInteractorImpl(private val remoteRepo: RemoteRepository<AppState>) :
    EnterCodeInteractor<AppState> {


    override suspend fun getUserByUPNFromDB(uid: String): AppState {
        return if (remoteRepo.getUserByUPNFromDB(uid) == null){
            AppState.Success(USER_IS_DISABLE_IN_DB)
        }else{
            remoteRepo.getUserByUPNFromDB(uid)
        }
    }


}