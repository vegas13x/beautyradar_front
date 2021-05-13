package com.nick_sib.beauty_radar.view_model

import android.app.Activity
import android.os.CountDownTimer
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.error.ToastError
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.auth.IAuthProvider
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.view.utils.INFINITY_LOADING_PROGRESS
import com.nick_sib.beauty_radar.view.utils.TAG_CODE_NULL
import com.nick_sib.beauty_radar.view.utils.TAG_DEBAG
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import com.nick_sib.beauty_radar.view_model.interactor.core.EnterCodeInteractor
import kotlinx.coroutines.launch

class EnterCodeViewModel(
    private val authProvider: IAuthProvider,
    private val interactor: EnterCodeInteractor<AppState>
) : BaseViewModel<AppState>() {
    val defSecondsLeft = 60

    val enterPin: Function1<String, Unit> = this::codeEntered
    val resendSMS: Function1<Activity?, Unit> = this::resendSMS

    val secondsLeft = ObservableField("60")

//    val errorDots = ObservableBoolean(false)
    val editedCode = ObservableField<Int?>()
    private var _editedCode: Int? = null
        set(value) {
//            errorDots.set(false)
            editedCode.set(value)
            field = value
        }

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

    init {
        startTimer()
    }

    fun checkUserInDB(uid: String?) {
        Log.d(TAG_DEBAG, "checkUserInDB: $uid")
        uid?.run {
            viewModelCoroutineScope.launch {
                val userFlag = interactor.existUserByUPNFromDB(uid)
                liveDataViewmodel.value = userFlag
            }
        }
    }

    private fun getTimerText(mills: Long): String {
        val total = mills / 1000
        val min = total / 60
        val sec = total % 60
        return if (min >= 0) String.format("%02d:%02d", min, sec) else String.format("%02d", sec)
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

    fun updateUserInDB(userDTO: UserDTO) {

        viewModelCoroutineScope.launch {
            Log.d("TAG111112", "updateUserInDB:"+ interactor.getToken())
            var user = UserDTO(
                userDTO.email,
                userDTO.id,
                userDTO.img,
                userDTO.login,
                userDTO.masterDTO,
                userDTO.name,
                userDTO.phone,
                userDTO.rating,
                userDTO.upn,
                interactor.getToken()
            )
            liveDataViewmodel.postValue(interactor.updateUser(userDTO.id))
        }
    }


    private fun codeEntered(code: String) {
        if (code.isEmpty()) {
            liveDataViewmodel.value = AppState.Error(ToastError(TAG_CODE_NULL))
        } else {
            liveDataViewmodel.value = AppState.Loading(INFINITY_LOADING_PROGRESS)
            viewModelCoroutineScope.launch {
                liveDataViewmodel.value = authProvider.verifyPhoneNumber(code)
            }
        }
    }

    override fun errorReturned(t: Throwable) {}


    private fun resendSMS(value: Activity?) {
        value?.run {
            liveDataViewmodel.value = AppState.Loading(INFINITY_LOADING_PROGRESS)
            viewModelCoroutineScope.launch {
                liveDataViewmodel.value =
                    authProvider.resentVerificationCode(this@run)
                _editedCode = null
            }
        }
    }

    fun addDigit(value: Int) {
        _editedCode = (_editedCode ?: 0) * 10 + value
        _editedCode?.run {
            if (this > 99999) {
                codeEntered(toString())
            }
        }
    }

    fun deleteDigit() {
        _editedCode = _editedCode?.let {
            if (it < 10) null else it / 10
        }
    }

    fun codeError() {
        _editedCode = null
//        errorDots.set(true)
    }
}