package com.nick_sib.beauty_radar.view.sign_up

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel

class SignUpViewModel: BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> {
        return liveDataViewmodel
    }


    override fun errorReturned(t: Throwable) {
        TODO("Not yet implemented")
    }

}