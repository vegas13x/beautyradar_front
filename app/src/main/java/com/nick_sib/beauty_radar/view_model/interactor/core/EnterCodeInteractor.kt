package com.nick_sib.beauty_radar.view_model.interactor.core

import com.nick_sib.beauty_radar.model.data.state.AppState

interface EnterCodeInteractor<S> : Interactor {
    suspend fun checkUserInDB(uid: String): AppState
}