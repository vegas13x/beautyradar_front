package com.nick_sib.beauty_radar.ui.profileScreen

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.profile.IRemoteDBProvider
import com.nick_sib.beauty_radar.ui.base.BaseViewModel

class ProfileViewModel(private val remoteDBProvider: IRemoteDBProvider) : BaseViewModel<AppState>() {

    fun subscribe(lifecycleOwner: LifecycleOwner): LiveData<AppState> {
        remoteDBProvider.getLiveDataProfileProvider().observe(lifecycleOwner, {
            liveDataViewmodel.value = it
        })
        return liveDataViewmodel

    }

    fun getUserProfileFromDb(uid: String) {
        getUserProfileFromDb(uid)
    }

    override fun errorReturned(t: Throwable) {
     //   TODO("Not yet implemented")
    }
}