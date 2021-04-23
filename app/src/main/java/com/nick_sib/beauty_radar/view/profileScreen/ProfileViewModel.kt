package com.nick_sib.beauty_radar.view.profileScreen

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.profile.IRemoteDBProviderProfile
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import kotlinx.coroutines.launch

class ProfileViewModel(private val remoteDBProviderProfile: IRemoteDBProviderProfile) :
    BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> {
        return liveDataViewmodel
    }

    fun getUserProfileFromDb(uid: String) {
        uid.run {
            viewModelCoroutineScope.launch {
                liveDataViewmodel.value = remoteDBProviderProfile.getUserFromDbByUID(uid)
            }
        }
    }

    override fun errorReturned(t: Throwable) {
        //   TODO("Not yet implemented")
    }
}