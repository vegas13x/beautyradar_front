package com.nick_sib.beauty_radar.ui.enter_code

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.error.ToastError
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.provider.profile.IRemoteDBProvider
import com.nick_sib.beauty_radar.ui.base.BaseViewModel
import com.nick_sib.beauty_radar.ui.utils.TAG_DEBAG

class EnterCodeViewModel(
    private val authProvider: IAuthProvider,
    private val dbProvider: IRemoteDBProvider
) : BaseViewModel<AppState>() {

    private val TAG_CODE_NULL = "Code is equal to null. Please enter the code"

    fun subscribe(lifecycleOwner: LifecycleOwner): LiveData<AppState> {
        subscribeLivedataAut(lifecycleOwner)
        subscribeLiveDataRemoteDB(lifecycleOwner)
        return liveDataViewmodel
    }

    private fun subscribeLivedataAut(lifecycleOwner: LifecycleOwner) {
        authProvider.getLiveDataAuthProvider().observe(lifecycleOwner, { appState ->
            liveDataViewmodel.value = appState
        })
    }

    private fun subscribeLiveDataRemoteDB(lifecycleOwner: LifecycleOwner) {
        dbProvider.getLiveDataProfileProvider().observe(lifecycleOwner, { appState ->
            liveDataViewmodel.value = appState
        })
    }

    fun checkUserInDB(uid: String) {
        dbProvider.getUser(uid)
    }

    fun codeEntered(code: String) {
        if (code.isNullOrEmpty()) {
            liveDataViewmodel.value = AppState.Error(ToastError(TAG_CODE_NULL))
        } else {
            Log.d(TAG_DEBAG, "codeEntered: КОД ОТПРАВЛЕН НА ПРОВЕРКУ")
            authProvider.verifyPhoneNumber(code)
        }
    }

    override fun errorReturned(t: Throwable) {
        // TODO("Not yet implemented")
    }

}