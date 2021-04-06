package com.nick_sib.beauty_radar.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.ui.utils.AUTH_SECCES_OPEN_NEXT_SCREEN
import com.nick_sib.beauty_radar.ui.utils.ERROR_ENTRY_TEXT

class LoginViewModel(val authProvider: IAuthProvider) : ViewModel() {

    private val liveDataLoginViewModel: MutableLiveData<AppState> = MutableLiveData()

    fun subscribeLiveData(): LiveData<AppState> {
        return liveDataLoginViewModel
    }


    fun createNewUser(email: String, password: String) {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            liveDataLoginViewModel.value = AppState.Error(ERROR_ENTRY_TEXT)
        } else {
            authProvider.singUpEmailAndPasswordUser(email, password)
            liveDataLoginViewModel.value = AppState.Loading(AUTH_SECCES_OPEN_NEXT_SCREEN)
        }
    }
}