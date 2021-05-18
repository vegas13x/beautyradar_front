package com.nick_sib.beauty_radar.view_model

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view.utils.FINISH_BUTTON_MASTER_REG
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel

class ProfileInfoEditViewModel: BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

    fun finishButton() {
        liveDataViewmodel.value = AppState.Success(FINISH_BUTTON_MASTER_REG)
    }
    override fun errorReturned(t: Throwable) {}
}