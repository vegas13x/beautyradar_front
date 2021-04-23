package com.nick_sib.beauty_radar.view.authScreen

import android.app.Activity
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import com.nick_sib.beauty_radar.view.utils.INFINITY_LOADING_PROGRESS
import kotlinx.coroutines.launch


class AuthViewModel(
    private val authProvider: IAuthProvider
) : BaseViewModel<AppState>() {

    private val phoneDigitsLength = 10
    val phoneError = ObservableInt(0)

    val signIn: Function1<Pair<String, Activity?>, Unit> = this::startPhoneNumberVerification

    fun subscribe(): LiveData<AppState> {
        return liveDataViewmodel
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


    fun codeDone() {
        liveDataViewmodel.value = AppState.Empty
    }

    override fun errorReturned(t: Throwable) {

    }
}