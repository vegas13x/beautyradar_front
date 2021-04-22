package com.nick_sib.beauty_radar.ui.profileScreen

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.profile.IRemoteDBProviderProfile
import com.nick_sib.beauty_radar.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ProfileViewModel(private val remoteDBProviderProfile: IRemoteDBProviderProfile) :
    BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> {
        return liveDataViewmodel
    }

    fun getUserProfileFromDb(uid: String) {
        uid.run {
            viewModelCoroutineScope.launch {
                remoteDBProviderProfile.getUserFromDbByUID(uid)
            }
        }
    }

    override fun errorReturned(t: Throwable) {
        //   TODO("Not yet implemented")
    }
}