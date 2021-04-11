package com.nick_sib.beauty_radar.ui.authScreen

import android.app.Activity
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.ui.base.BaseViewModel

class AuthViewModel(private val authProvider: IAuthProvider) : BaseViewModel<AppState>() {

    private val phoneDigitsLength = 10


//    private val _phoneError = MutableLiveData("false")
    val phoneError = ObservableBoolean(false)
//        get() = _phoneError

    fun subscribe(lifecycleOwner: LifecycleOwner): LiveData<AppState> {
        authProvider.getLiveDataAuthProvider().observe(lifecycleOwner, { appState ->
            liveDataViewmodel.value = appState
        })
        return liveDataViewmodel
    }

    private fun checkPhone(value: String): Boolean =
        (value.length == phoneDigitsLength).also {
            phoneError.set(!it)
        }

    fun startPhoneNumberVerification(activity: Activity, phone: String) {
        if (checkPhone(phone)) {
            authProvider.startPhoneNumberVerification(activity, "+7$phone")
        }
    }

    override fun errorReturned(t: Throwable) {

    }
}