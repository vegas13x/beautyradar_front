package com.nick_sib.beauty_radar.ui.sign_up_second

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.profile.IRemoteDBProvider
import com.nick_sib.beauty_radar.provider.profile.entities.UserProfile
import com.nick_sib.beauty_radar.ui.base.BaseViewModel


class SignUpSecondViewModel(private val remoteDBProvider: IRemoteDBProvider): BaseViewModel<AppState>(){


    fun subscribe(): LiveData<AppState> {
        return liveDataViewmodel
    }


    fun createNewUser(uid: String, name: String, secondName: String , job: String){
        var user = UserProfile(uid,name,secondName,null,null,job,
            null,null,null,null,null,null,null)
        remoteDBProvider.createUserInDb(user)
    }

    override fun errorReturned(t: Throwable) {
        TODO("Not yet implemented")
    }


}