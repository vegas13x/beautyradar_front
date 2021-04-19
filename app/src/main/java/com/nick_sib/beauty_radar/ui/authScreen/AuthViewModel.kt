package com.nick_sib.beauty_radar.ui.authScreen

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableInt
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.ui.base.BaseViewModel
import com.nick_sib.beauty_radar.ui.utils.INFINITY_LOADING_PROGRESS
import kotlinx.coroutines.launch


class AuthViewModel(private val authProvider: IAuthProvider) : BaseViewModel<AppState>() {

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
                        authProvider.startPhoneNumberVerification(this@run, "+7${value.first}")
                }
            }

            var intent = Intent(this@run, FirebaseMessagingService::class.java)
            startService(intent)
        }
    }



    fun codeDone() {
        liveDataViewmodel.value = AppState.Empty()
    }

    override fun errorReturned(t: Throwable) {

    }
}