package com.nick_sib.beauty_radar.ui.login

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.ui.base.BaseViewModel
import com.nick_sib.beauty_radar.ui.utils.AUTH_SECCES_OPEN_NEXT_SCREEN
import com.nick_sib.beauty_radar.ui.utils.ERROR_ENTRY_TEXT

class LoginViewModel(val authProvider: IAuthProvider) : BaseViewModel<AppState>() {



    fun subscribeLiveData(lifecycleOwner: LifecycleOwner): LiveData<AppState> {
        authProvider.getLiveDataAuthProvider().observe(lifecycleOwner, {
            liveDataViewmodel.value = it
        })
        return liveDataViewmodel
    }

    fun login(email: String, password: String) {
        authProvider.signInEmailPassword(email, password)
    }

    override fun errorReturned(t: Throwable) {
        TODO("Not yet implemented")
    }


}