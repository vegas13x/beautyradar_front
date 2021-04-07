package com.nick_sib.beauty_radar.ui.logout


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.ui.base.BaseViewModel

class LogoutViewModel(private val authProvider: IAuthProvider) : BaseViewModel<AppState>() {

    fun subscribeLiveData(lifecycleOwner: LifecycleOwner): LiveData<AppState> {
        authProvider.getLiveDataAuthProvider().observe(lifecycleOwner, {
            liveDataViewmodel.value = it
        })
        return liveDataViewmodel
    }

    fun exitInProfile() {
        authProvider.signOut()
    }

    override fun errorReturned(t: Throwable) {
        TODO("Not yet implemented")
    }

}