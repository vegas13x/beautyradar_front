package com.nick_sib.beauty_radar.view_model

import android.app.Activity
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.entites.FragmentType
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.auth.IAuthProvider
import com.nick_sib.beauty_radar.view.utils.INFINITY_LOADING_PROGRESS
import kotlinx.coroutines.launch

class SignInViewModel (
    private val authProvider: IAuthProvider
) : SignViewModel<AppState>() {

    init {
        _fragmentType.value = FragmentType.SIGNIN
    }

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

    override fun startPhoneNumberVerification(value: Pair<String, Activity?>) {
//        value.second?.run {
//            if (checkPhone(value.first)) {
//                liveDataViewmodel.value = AppState.Loading(INFINITY_LOADING_PROGRESS)
//                viewModelCoroutineScope.launch {
//                    liveDataViewmodel.value =
//                        authProvider.startPhoneNumberVerification(this@run,"+7${value.first}")
//                }
//            }
//        }
    }

    fun codeDone() {
        liveDataViewmodel.value = AppState.Empty
    }

    override fun errorReturned(t: Throwable) {}
}