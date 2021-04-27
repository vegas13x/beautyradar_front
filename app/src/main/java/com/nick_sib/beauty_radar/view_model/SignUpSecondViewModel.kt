package com.nick_sib.beauty_radar.view_model

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.profile.IRemoteDBProviderProfile
import com.nick_sib.beauty_radar.model.provider_new.repository.user.MasterDTO
import com.nick_sib.beauty_radar.model.provider_new.repository.user.UserDTO
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import com.nick_sib.beauty_radar.view_model.interactor.core.SignUpInteractor
import kotlinx.coroutines.launch


class SignUpSecondViewModel(
    private val remoteDBProviderProfile: IRemoteDBProviderProfile,
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
//        var user = UserProfile(
//            uid, name, secondName, null, null, master, client,
//            null, null, null, null, null, null, null
//        )
//        remoteDBProviderProfile.createUserInDb(user)
        var master = MasterDTO("Moscow", 0, 5)
        var user = UserDTO(
            "tyty@mail.ru",
            0,
            "null",
            "+999999999",
            master,
            "Anna",
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