package com.nick_sib.beauty_radar.view_model.interactor.impl

import android.util.Log
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.view.utils.TAG_DEBAG
import com.nick_sib.beauty_radar.view.utils.USER_IS_DISABLE_IN_DB
import com.nick_sib.beauty_radar.view_model.interactor.core.EnterCodeInteractor

class EnterCodeInteractorImpl(private val remoteRepo: RemoteRepository<AppState>) :
    EnterCodeInteractor<AppState> {


    override suspend fun checkUserInDB(uid: String): AppState {
        return if (remoteRepo.checkUserInDB(uid) == null){
            Log.d(TAG_DEBAG, "renderData: ${remoteRepo.checkUserInDB(uid)} ")
            AppState.Success(USER_IS_DISABLE_IN_DB)
        }else{
            Log.d(TAG_DEBAG, "renderData: ${remoteRepo.checkUserInDB(uid)} ")
            remoteRepo.checkUserInDB(uid)
        }
    }


}