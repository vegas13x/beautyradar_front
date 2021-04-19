package com.nick_sib.beauty_radar.room.roomView

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.room.IRoomSource
import com.nick_sib.beauty_radar.ui.base.BaseViewModel

class RoomViewModel(private val authProvider: IAuthProvider, private val iRoomSource: IRoomSource) :
    BaseViewModel<AppState>() {

    fun subscribe(lifecycleOwner: LifecycleOwner): LiveData<AppState> {
        subscribeLiveDataAuth(lifecycleOwner)
        return liveDataViewmodel
    }

    private fun subscribeLiveDataAuth(lifecycleOwner: LifecycleOwner) {
//        authProvider.getLiveDataAuthProvider().observe(lifecycleOwner, { appState ->
//            liveDataViewmodel.value = appState
//        })
    }

    fun getData() {
        Thread {
            var list = iRoomSource.getData()
            Log.d("TAG22222", "getData: $list")
        }.start()

    }


    override fun errorReturned(t: Throwable) {
        TODO("Not yet implemented")
    }
}