package com.nick_sib.beauty_radar.ui.authScreen

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.ui.base.BaseViewModel

class AuthViewModel(private val authProvider: IAuthProvider) : BaseViewModel<AppState>() {

    fun subscribe(lifecycleOwner: LifecycleOwner): LiveData<AppState> {
        authProvider.getLiveDataAuthProvider().observe(lifecycleOwner, { appState ->
            liveDataViewmodel.value = appState
        })
        return liveDataViewmodel
    }

    fun startPhoneNumberVerification(activity: Activity, phone: String) {
        if (phone.isNullOrEmpty()){
            liveDataViewmodel.value = AppState.Error("Введите номер телефона")
        }else{
        authProvider.startPhoneNumberVerification(activity, phone)
        }
    }

    override fun errorReturned(t: Throwable) {
      //  TODO("Not yet implemented")
    }
}