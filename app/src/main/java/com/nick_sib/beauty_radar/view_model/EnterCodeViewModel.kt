package com.nick_sib.beauty_radar.view_model

import android.app.Activity
import android.os.CountDownTimer
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.SingletonImgUrl
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.auth.IAuthProvider
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.view.utils.INFINITY_LOADING_PROGRESS
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import com.nick_sib.beauty_radar.view_model.interactor.core.EnterCodeInteractor
import kotlinx.coroutines.launch

class EnterCodeViewModel(
    private val authProvider: IAuthProvider,
    private val interactor: EnterCodeInteractor<AppState>
) : BaseViewModel<AppState>() {

    val defSecondsLeft = 60
    val enterPin: Function1<String, Unit> = this::checkSMS
    val secondsLeft = ObservableField("60")
    val haveError = ObservableField(false)

    init {
        startTimer()
    }

    private fun getTimerText(mills: Long): String {
        val total = mills / 1000
        val min = total / 60
        val sec = total % 60
        return if (min > 0) String.format("%02d:%02d", min, sec) else String.format("%02d", sec)
    }

    private fun startTimer() {
        object : CountDownTimer((defSecondsLeft * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondsLeft.set(if (millisUntilFinished > 0) getTimerText(millisUntilFinished) else null)
            }
            override fun onFinish() {
                secondsLeft.set(null)
            }
        }.start()
    }

    private fun checkSMS(code: String) {
        liveDataViewmodel.value = AppState.Loading(INFINITY_LOADING_PROGRESS)
        viewModelCoroutineScope.launch {
            liveDataViewmodel.value = authProvider.verifyPhoneNumber(code)
        }
    }

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

    fun checkUserInDB(uid: String?) {
        uid?.run {
            viewModelCoroutineScope.launch {
                val userFlag = interactor.existUserByUPNFromDB(uid)
                liveDataViewmodel.value = userFlag
            }
        }
    }

    fun getUserByUID(upn: String?) {
        upn?.run {
            viewModelCoroutineScope.launch {
                val userDTO = interactor.getUserByUPNFromDB(upn)
                liveDataViewmodel.value = userDTO
            }
        }
    }

    fun setImgInSingleton(imgUrl: String?) = SingletonImgUrl.setImgUrl(imgUrl)

    fun updateUserByUserResponse(userDTO: UserDTO) =
        viewModelCoroutineScope.launch {
            userDTO.token = interactor.getToken()
            interactor.updateUser(userDTO.id, userDTO)
        }

    fun resendSMS(activity: Activity) {
        haveError.set(false)
        liveDataViewmodel.value = AppState.Loading(INFINITY_LOADING_PROGRESS)
        startTimer()
        viewModelCoroutineScope.launch {
            liveDataViewmodel.value =
                authProvider.resentVerificationCode(activity)
        }
    }

    fun codeError() {
        haveError.set(true)
    }

    override fun errorReturned(t: Throwable) {}
}