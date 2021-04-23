package com.nick_sib.beauty_radar.model.repository.impl

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.calendar.IRemoteDBProviderCalendar
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository

class RemoteRepositoryImpl(private val remoteDB: IRemoteDBProviderCalendar) :
    RemoteRepository<AppState> {

    override fun getData(): AppState {
        return AppState.Success(remoteDB.test_getListData())
    }
}