package com.nick_sib.beauty_radar.view_model

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import kotlinx.coroutines.launch

class LogoutViewModel(private val authProvider: IAuthProvider) : BaseViewModel<AppState>() {

    fun subscribeLiveData(): LiveData<AppState> {
        return liveDataViewmodel
    }

    fun exitInProfile() {
        viewModelCoroutineScope.launch {
            liveDataViewmodel.value = authProvider.signOut()
        }

    }

    override fun errorReturned(t: Throwable) {
      //  TODO("Not yet implemented")
    }

}