package com.nick_sib.beauty_radar.view_model

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import com.nick_sib.beauty_radar.view_model.interactor.core.ProfileInteractor
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val interactor: ProfileInteractor<AppState>
) :
    BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> {
        return liveDataViewmodel
    }

    fun getUserProfileFromDb(uid: String) {
        uid.run {
            viewModelCoroutineScope.launch {
                liveDataViewmodel.postValue(interactor.getUserByUPNFromDB(uid))
            }
        }
    }

    override fun errorReturned(t: Throwable) {
        //   TODO("Not yet implemented")
    }
}