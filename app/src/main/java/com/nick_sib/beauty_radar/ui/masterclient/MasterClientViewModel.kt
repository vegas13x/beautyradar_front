package com.nick_sib.beauty_radar.ui.masterclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.calendar.IRemoteDBProviderCalendar
import com.nick_sib.beauty_radar.provider.calendar.entities.CalendarProfile
import com.nick_sib.beauty_radar.ui.base.BaseViewModel

class MasterClientViewModel(private val remoteDB: IRemoteDBProviderCalendar) :
    BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> {
        return liveDataViewmodel
    }

    fun getListClients() {
        liveDataViewmodel.value =
            AppState.Success(remoteDB.test_getListData())
    }

    override fun errorReturned(t: Throwable) {
        TODO("Not yet implemented")
    }

}