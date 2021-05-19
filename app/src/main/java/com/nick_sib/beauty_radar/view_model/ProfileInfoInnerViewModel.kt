package com.nick_sib.beauty_radar.view_model

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import com.nick_sib.beauty_radar.view_model.interactor.core.ProfileInfoEditInteractor
import kotlinx.coroutines.launch

class ProfileInfoInnerViewModel(private val interactor: ProfileInfoEditInteractor<AppState>) : BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

    override fun errorReturned(t: Throwable) {}

    fun getInfo() {
        viewModelCoroutineScope.launch {
            interactor.getUserFromDB()
            liveDataViewmodel.value = interactor.getUserFromDB()
        }
    }
}