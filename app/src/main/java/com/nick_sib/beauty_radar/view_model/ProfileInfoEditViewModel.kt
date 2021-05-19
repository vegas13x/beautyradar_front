package com.nick_sib.beauty_radar.view_model

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.master.UserMasterProfile
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import com.nick_sib.beauty_radar.view_model.interactor.core.ProfileInfoEditInteractor
import kotlinx.coroutines.launch

class ProfileInfoEditViewModel(private val interactor: ProfileInfoEditInteractor<AppState>): BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

    override fun errorReturned(t: Throwable) {}

    fun setInfoAboutUser(name: String, surname: String, address: String, phone: String,
                                 birthdayDate: String, aboutUrself: String){
        var userMasterProfile = UserMasterProfile(name,surname,address,phone,birthdayDate,aboutUrself)

        viewModelCoroutineScope.launch {
            interactor.sendUserToDB(userMasterProfile)
        }

    }

    fun getInfoAboutUser() {
        viewModelCoroutineScope.launch {
            interactor.getUserFromDB()
            liveDataViewmodel.value = interactor.getUserFromDB()
        }
    }

}