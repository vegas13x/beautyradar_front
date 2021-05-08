package com.nick_sib.beauty_radar.view_model

import android.app.Activity
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nick_sib.beauty_radar.model.data.entites.FragmentType
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel

abstract class SignViewModel<T: AppState> : BaseViewModel<T>() {
    protected val _fragmentType = MutableLiveData<FragmentType>()
    val fragmentType: LiveData<FragmentType>
        get() = _fragmentType

    val phoneError = ObservableBoolean(false)

    private val phoneDigitsLength = 10

    protected fun checkPhone(value: String): Boolean =
        (value.length == phoneDigitsLength).also {
            phoneError.set(!it)
        }

    val signIn: Function1<Pair<String, Activity?>, Unit> = this::startPhoneNumberVerification

    abstract fun startPhoneNumberVerification(value: Pair<String, Activity?>)

}