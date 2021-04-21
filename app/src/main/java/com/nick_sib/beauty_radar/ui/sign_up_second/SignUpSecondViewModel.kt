package com.nick_sib.beauty_radar.ui.sign_up_second

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.profile.IRemoteDBProviderProfile
import com.nick_sib.beauty_radar.provider.profile.entities.UserProfile
import com.nick_sib.beauty_radar.ui.base.BaseViewModel


class SignUpSecondViewModel(private val remoteDBProviderProfile: IRemoteDBProviderProfile) :
    BaseViewModel<AppState>() {


    fun subscribe(): LiveData<AppState> {
        return liveDataViewmodel
    }

    fun createNewUser(
        uid: String,
        name: String,
        secondName: String,
        master: Boolean,
        client: Boolean
    ) {
        var user = UserProfile(
            uid, name, secondName, null, null, master, client,
            null, null, null, null, null, null, null
        )
        remoteDBProviderProfile.createUserInDb(user)
    }

    override fun errorReturned(t: Throwable) {
        TODO("Not yet implemented")
    }


}