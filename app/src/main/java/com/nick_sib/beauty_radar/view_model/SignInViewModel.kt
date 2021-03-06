package com.nick_sib.beauty_radar.view_model

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import com.nick_sib.beauty_radar.view_model.interactor.core.SignInInteractor
import kotlinx.coroutines.launch

class SignInViewModel(private val interactor: SignInInteractor<AppState>) : BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

    fun createNewUser(uid: String, name: String, job: String?) {
        viewModelCoroutineScope.launch {
            var user = UserDTO(null, uid, interactor.getToken(),
                "+72", name, "+79", null, null)
            uid.run {
                liveDataViewmodel.postValue(interactor.createUser(user))
            }
        }
    }

    override fun errorReturned(t: Throwable) {}

}