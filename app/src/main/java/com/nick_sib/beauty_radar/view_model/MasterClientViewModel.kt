package com.nick_sib.beauty_radar.view_model

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import com.nick_sib.beauty_radar.view_model.interactor.core.MasterClientInteractor

class MasterClientViewModel(private val interactor: MasterClientInteractor<AppState>) :
    BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

    override fun errorReturned(t: Throwable) {}
}


