package com.nick_sib.beauty_radar.ui.sign_up

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.ui.base.BaseViewModel

class SignUpViewModel: BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> {
        return liveDataViewmodel
    }


    override fun errorReturned(t: Throwable) {
        TODO("Not yet implemented")
    }

}