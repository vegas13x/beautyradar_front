package com.nick_sib.beauty_radar.ui.profileScreen

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.profile.IRemoteDBProvider
import com.nick_sib.beauty_radar.provider.profile.entities.UserProfile
import com.nick_sib.beauty_radar.ui.base.BaseViewModel

class ProfileViewModel(private val profileProvider: IRemoteDBProvider) : BaseViewModel<AppState>() {

    fun subscribe(lifecycleOwner: LifecycleOwner): LiveData<AppState> {
        profileProvider.getLiveDataProfileProvider().observe(lifecycleOwner, {
            liveDataViewmodel.value = it
        })
        return liveDataViewmodel
    }

    fun verifyUID(verify: String) {
    }

    fun createUser(user: UserProfile) {
        Log.d("sadsadasdasdasd", "createUser: user: UserProfile")
        profileProvider.createUserInDb(user)
    }

    override fun errorReturned(t: Throwable) {
     //   TODO("Not yet implemented")
    }
}