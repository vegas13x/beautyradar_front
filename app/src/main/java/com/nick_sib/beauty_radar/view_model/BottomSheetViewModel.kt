package com.nick_sib.beauty_radar.view_model

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import com.nick_sib.beauty_radar.view_model.interactor.core.BottomSheetInteractor
import kotlinx.coroutines.launch

class BottomSheetViewModel(private val interactor: BottomSheetInteractor<AppState>): BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

    fun getListService() {
        viewModelCoroutineScope.launch {
            liveDataViewmodel.value = interactor.getService()
        }
    }

    override fun errorReturned(t: Throwable) {}
}