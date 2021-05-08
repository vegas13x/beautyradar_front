package com.nick_sib.beauty_radar.view_model

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.user.MasterDTO
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import com.nick_sib.beauty_radar.view_model.interactor.core.SignUpInteractor
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext


class SignUpSecondViewModel(private val interactor: SignUpInteractor<AppState>) :
    BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

    fun createNewUser(uid: String, name: String, job: String?) {
        var master = MasterDTO(null, 100, 0)
        viewModelCoroutineScope.launch {
            var user = UserDTO(
                null, 0, null, "+72",
                master, name, null, 5, uid, interactor.getToken())
            uid.run {
                liveDataViewmodel.postValue(interactor.createUser(user))
            }
        }
    }


    override fun errorReturned(t: Throwable) {}

}