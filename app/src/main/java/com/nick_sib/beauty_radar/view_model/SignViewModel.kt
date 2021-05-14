package com.nick_sib.beauty_radar.view_model

import android.app.Activity
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.entites.FragmentType
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.auth.IAuthProvider
import com.nick_sib.beauty_radar.view.utils.INFINITY_LOADING_PROGRESS
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import kotlinx.coroutines.launch

class SignViewModel(
    private val authProvider: IAuthProvider
) : BaseViewModel<AppState>() {


    val phoneError = ObservableBoolean(false)
    val fragmentType = ObservableField(FragmentType.SIGNUP)

    private val phoneDigitsLength = 10

    val signIn: Function1<Pair<String, Activity?>, Unit> = this::startPhoneNumberVerification

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

    private fun checkPhone(value: String): Boolean =
        (value.length == phoneDigitsLength).also {
            phoneError.set(!it)
        }

    fun setType(value: FragmentType){
        fragmentType.set(value)
    }

    fun switchType(){
        fragmentType.get()?.run {
            fragmentType.set(next())
        }
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

    override fun errorReturned(t: Throwable) {}
}