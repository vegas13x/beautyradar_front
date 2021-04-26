package com.nick_sib.beauty_radar.view_model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel

class InitialProfileSetupViewModel(private val authProvider: IAuthProvider) :
    BaseViewModel<AppState>() {
    fun sucscribeLiveData(lifecycleOwner: LifecycleOwner): LiveData<AppState> {
//        authProviderFrAuth.getLiveDataAuthProvider().observe(lifecycleOwner, {
//            liveDataViewmodel.value = it
//        })
        return liveDataViewmodel
    }

    fun addEmailAndPasswordInProfile(email: String, password: String) {
//        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
//            liveDataViewmodel.value = AppState.Error(ToastError("Email or Password null"))
//        } else {
//            authProviderFrAuth.addEmailAndPasswordInCurrentUser(email, password)
//
//        }
    }

    override fun errorReturned(t: Throwable) {
        // TODO("Not yet implemented")
    }

}