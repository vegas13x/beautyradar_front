package com.nick_sib.beauty_radar.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nick_sib.beauty_radar.data.state.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState>(
    protected val liveDataViewmodel: MutableLiveData<T> = MutableLiveData()
) : ViewModel() {

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            errorReturned(throwable)
        })

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    abstract fun errorReturned(t: Throwable)
}