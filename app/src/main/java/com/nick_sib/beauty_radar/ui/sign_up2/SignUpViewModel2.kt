package com.nick_sib.beauty_radar.ui.sign_up2

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.profile.IRemoteDBProvider
import com.nick_sib.beauty_radar.provider.profile.entities.UserProfile
import com.nick_sib.beauty_radar.ui.base.BaseViewModel
import com.nick_sib.beauty_radar.ui.sign_up.SignUpViewModel


class SignUpViewModel2(private val remoteDBProvider: IRemoteDBProvider): BaseViewModel<AppState>(){


    fun subscribe(lifecycleOwner: LifecycleOwner): LiveData<AppState> {
        remoteDBProvider.getLiveDataProfileProvider().observe(lifecycleOwner, {
            liveDataViewmodel.value = it
        })
        return liveDataViewmodel


    }


    fun createNewUser(uid: String, name: String, secondName: String , job: String){
        var user = UserProfile(uid,name,secondName,null,null,job,null,null,null,null,null,null,null)
        remoteDBProvider.createUserInDb(user)
    }

    override fun errorReturned(t: Throwable) {
        TODO("Not yet implemented")
    }


}