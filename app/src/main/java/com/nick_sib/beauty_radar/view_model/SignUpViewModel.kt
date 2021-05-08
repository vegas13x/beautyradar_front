package com.nick_sib.beauty_radar.view_model

import android.app.Activity
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nick_sib.beauty_radar.model.data.entites.FragmentType
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.auth.IAuthProvider
import com.nick_sib.beauty_radar.view.utils.INFINITY_LOADING_PROGRESS
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import kotlinx.coroutines.launch


class SignUpViewModel(
    private val authProvider: IAuthProvider
) : BaseViewModel<AppState>() {

    private val _fragmentType = MutableLiveData<FragmentType>()
    val fragmentType: LiveData<FragmentType>
        get() = _fragmentType

    val phoneError = ObservableBoolean(false)

    private val phoneDigitsLength = 10

    private fun checkPhone(value: String): Boolean =
        (value.length == phoneDigitsLength).also {
            phoneError.set(!it)
        }

    val signIn: Function1<Pair<String, Activity?>, Unit> = this::startPhoneNumberVerification

    init {
        _fragmentType.value = FragmentType.SIGNUP
    }

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

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