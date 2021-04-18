package com.nick_sib.beauty_radar.ui.authScreen

import android.app.Activity
import android.util.Log
import androidx.databinding.ObservableInt
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.ui.base.BaseViewModel
import com.nick_sib.beauty_radar.ui.utils.INFINITY_LOADING_PROGRESS
import kotlinx.coroutines.launch
import kotlin.coroutines.resume


class AuthViewModel(
    private val authProvider: IAuthProvider
) : BaseViewModel<AppState>() {

    private val phoneDigitsLength = 10
    val phoneError = ObservableInt(0)

    val signIn: Function1<Pair<String, Activity?>, Unit> = this::startPhoneNumberVerification

    fun subscribe(lifecycleOwner: LifecycleOwner): LiveData<AppState> {
        subscribeLiveDataAuth(lifecycleOwner)
        return liveDataViewmodel
    }

    private fun subscribeLiveDataAuth(lifecycleOwner: LifecycleOwner) {
        authProvider.getLiveDataAuthProvider().observe(lifecycleOwner, { appState ->
            liveDataViewmodel.value = appState
        })
    }

    private fun checkPhone(value: String): Boolean =
        (value.length == phoneDigitsLength).also {
            phoneError.set(if (it) 0 else R.string.s_phone_error)
        }

    private fun startPhoneNumberVerification(value: Pair<String, Activity?>) {
        value.second?.run {
            if (checkPhone(value.first)) {
                liveDataViewmodel.value = AppState.Loading(INFINITY_LOADING_PROGRESS)
                viewModelCoroutineScope.launch {
                    liveDataViewmodel.value =
                        authProvider.startPhoneNumberVerification(this@run,"+7${value.first}")
                }
            }
        }
    }

    override fun errorReturned(t: Throwable) {

    }

}