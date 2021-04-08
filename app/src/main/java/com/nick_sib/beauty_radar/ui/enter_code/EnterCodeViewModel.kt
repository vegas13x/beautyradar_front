package com.nick_sib.beauty_radar.ui.enter_code

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.ui.base.BaseViewModel

class EnterCodeViewModel(private val authProvider: IAuthProvider) : BaseViewModel<AppState>() {

    private val TAG_CODE_NULL = "Code is equal to null. Please enter the code"

    fun subscribe(lifecycleOwner: LifecycleOwner): LiveData<AppState> {
        authProvider.getLiveDataAuthProvider().observe(lifecycleOwner, { appState ->
            liveDataViewmodel.value = appState
        })
        return liveDataViewmodel
    }

    fun codeEntered(code: String) {
        val localCode = code
        if (localCode.isNullOrEmpty()) {
            liveDataViewmodel.value = AppState.Error(TAG_CODE_NULL)
        } else {
            authProvider.verifyPhoneNumber(code)
        }
    }

    override fun errorReturned(t: Throwable) {
        TODO("Not yet implemented")
    }

}