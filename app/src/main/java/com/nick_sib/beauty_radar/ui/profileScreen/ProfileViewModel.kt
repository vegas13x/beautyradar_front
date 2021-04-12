package com.nick_sib.beauty_radar.ui.profileScreen

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.profile.IProfileProvider
import com.nick_sib.beauty_radar.ui.base.BaseViewModel

class ProfileViewModel(private val profileProvider: IProfileProvider) : BaseViewModel<AppState>() {

    fun subscribe(lifecycleOwner: LifecycleOwner): LiveData<AppState> {
        profileProvider.getLiveDataProfileProvider().observe(lifecycleOwner, {
            liveDataViewmodel.value = it
        })
        return liveDataViewmodel
    }

    fun verifyUID(verify: String) {
        val uid = verify
        profileProvider.checkUIDUser(uid)
    }

    override fun errorReturned(t: Throwable) {
     //   TODO("Not yet implemented")
    }
}