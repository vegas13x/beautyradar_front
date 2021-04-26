package com.nick_sib.beauty_radar.view_model

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.calendar.IRemoteDBProviderCalendar
import com.nick_sib.beauty_radar.view.utils.TRANSITION_TO_CALENDAR
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import com.nick_sib.beauty_radar.view_model.interactor.core.MasterClientInteractor

class MasterClientViewModel(private val interactor: MasterClientInteractor<AppState>) :
    BaseViewModel<AppState>() {



  fun transitionToCalendar() {
        liveDataViewmodel.value = AppState.Success(TRANSITION_TO_CALENDAR)
    }

    fun subscribe(): LiveData<AppState> {
        return liveDataViewmodel
    }

    fun getListClients() {
        liveDataViewmodel.value =
            interactor.getData()
    }

    override fun errorReturned(t: Throwable) {
        TODO("Not yet implemented")
    }

}