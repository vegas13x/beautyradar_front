package com.nick_sib.beauty_radar.ui.authScreen

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.error.HintError
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.ui.base.BaseViewModel

class AuthViewModel(private val authProvider: IAuthProvider) : BaseViewModel<AppState>() {

    private val phoneDigitsLength = 10

    fun subscribe(lifecycleOwner: LifecycleOwner): LiveData<AppState> {
        authProvider.getLiveDataAuthProvider().observe(lifecycleOwner, { appState ->
            liveDataViewmodel.value = appState
        })
        return liveDataViewmodel
    }

    private fun checkPhone(value: String): Boolean =
        value.length == phoneDigitsLength

    fun startPhoneNumberVerification(activity: Activity, phone: String) {
        if (checkPhone(phone)) {
            authProvider.startPhoneNumberVerification(activity, "+7$phone")
        } else
            liveDataViewmodel.postValue(AppState.Error(HintError("have it")))
    }

    override fun errorReturned(t: Throwable) {

    }
}