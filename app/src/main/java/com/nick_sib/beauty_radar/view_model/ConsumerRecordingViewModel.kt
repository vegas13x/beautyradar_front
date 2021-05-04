package com.nick_sib.beauty_radar.view_model

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view.utils.OPEN_BOTTOM_SHEET_CHOOSE_SERVICE
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel

class ConsumerRecordingViewModel : BaseViewModel<AppState>() {
    override fun errorReturned(t: Throwable) {
        liveDataViewmodel.value = AppState.Error(t)
    }

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

    fun openSheetService(){
        liveDataViewmodel.value = AppState.Success(OPEN_BOTTOM_SHEET_CHOOSE_SERVICE)
    }

}