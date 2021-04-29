package com.nick_sib.beauty_radar.view_model

import android.app.Activity
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.error.ToastError
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.auth.IAuthProvider
import com.nick_sib.beauty_radar.view.utils.INFINITY_LOADING_PROGRESS
import com.nick_sib.beauty_radar.view.utils.TAG_CODE_NULL
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import com.nick_sib.beauty_radar.view_model.interactor.core.EnterCodeInteractor
import kotlinx.coroutines.launch

class EnterCodeViewModel(
    private val authProvider: IAuthProvider,
    private val interactor: EnterCodeInteractor<AppState>
) : BaseViewModel<AppState>() {

    val resendSMS: Function1<Activity?, Unit> = this::resendSMS

    val errorDots = ObservableBoolean(false)
    val editedCode = ObservableField<Int?>()
    private var _editedCode: Int? = null
        set(value) {
            errorDots.set(false)
            editedCode.set(value)
            field = value
        }

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

    fun checkUserInDB(uid: String?) {
        uid?.run {
            viewModelCoroutineScope.launch {
                liveDataViewmodel.value = interactor.getUserByUPNFromDB(uid)
            }
        }
    }

    private fun codeEntered(code: String) {
        if (code.isEmpty()) {
            liveDataViewmodel.value = AppState.Error(ToastError(TAG_CODE_NULL))
        } else {
            liveDataViewmodel.value = AppState.Loading(INFINITY_LOADING_PROGRESS)
            viewModelCoroutineScope.launch {
                liveDataViewmodel.value = authProvider.verifyPhoneNumber(code)
            }
        }
    }

    override fun errorReturned(t: Throwable) {}


    private fun resendSMS(value: Activity?) {
        value?.run {
            liveDataViewmodel.value = AppState.Loading(INFINITY_LOADING_PROGRESS)
            viewModelCoroutineScope.launch {
                liveDataViewmodel.value =
                    authProvider.resentVerificationCode(this@run)
                _editedCode = null
            }
        }
    }

    fun addDigit(value: Int) {
        _editedCode = (_editedCode ?: 0) * 10 + value
        _editedCode?.run {
            if (this > 99999) {
                codeEntered(toString())
            }
        }
    }

    fun deleteDigit() {
        _editedCode = _editedCode?.let {
            if (it < 10) null else it / 10
        }
    }

    fun codeError() {
        _editedCode = null
        errorDots.set(true)
    }
}