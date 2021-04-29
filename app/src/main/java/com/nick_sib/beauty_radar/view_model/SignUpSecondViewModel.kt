package com.nick_sib.beauty_radar.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.iid.FirebaseInstanceId
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.user.MasterDTO
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.view.utils.TAG_DEBAG
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import com.nick_sib.beauty_radar.view_model.interactor.core.SignUpInteractor
import kotlinx.coroutines.launch

class SignUpSecondViewModel(private val interactor: SignUpInteractor<AppState>) :
    BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

    fun createNewUser(uid: String, name: String, job: String?) {
        var master = MasterDTO(null, 100, 0)
        var token =  FirebaseInstanceId.getInstance().token
        var user = UserDTO(null, 0, null, null,
            master, name, null, 5, uid, token)
        uid.run {
            viewModelCoroutineScope.launch {
                liveDataViewmodel.postValue(interactor.createUser(user))
            }
        }

    }

    override fun errorReturned(t: Throwable) {}

}