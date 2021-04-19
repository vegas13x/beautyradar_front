package com.nick_sib.beauty_radar.ui.profileScreen

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.ui.base.BaseViewModel

class ProfileViewModel : BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> {
        return liveDataViewmodel
    }

    fun getUserProfileFromDb(uid: String) {
        getUserProfileFromDb(uid)
    }

    override fun errorReturned(t: Throwable) {
     //   TODO("Not yet implemented")
    }
}