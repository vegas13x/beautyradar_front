package com.nick_sib.beauty_radar.view_model

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider_new.repository.user.MasterDTO
import com.nick_sib.beauty_radar.model.provider_new.repository.user.UserDTO
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import com.nick_sib.beauty_radar.view_model.interactor.core.SignUpInteractor
import kotlinx.coroutines.launch


class SignUpSecondViewModel(
    private val interactor: SignUpInteractor<AppState>
) :
    BaseViewModel<AppState>() {


    fun subscribe(): LiveData<AppState> {
        return liveDataViewmodel
    }

    fun createNewUser(
        uid: String,
        name: String,
        secondName: String,
        master: String?,
        client: String?
    ) {
        var master = MasterDTO("Russia", 0, 0)
        var user = UserDTO(
            "tyty@mail.ru",
            0,
            "null",
            "+999999999",
            master,
            name,
            "+999999999",
            5,
            "+999999999"
        )
        uid.run {
            viewModelCoroutineScope.launch {
                liveDataViewmodel.postValue(interactor.createUser(user))
            }
        }

    }

    override fun errorReturned(t: Throwable) {
        TODO("Not yet implemented")
    }


}