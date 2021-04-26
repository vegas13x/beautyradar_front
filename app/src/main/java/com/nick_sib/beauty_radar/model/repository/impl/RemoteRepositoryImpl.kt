package com.nick_sib.beauty_radar.model.repository.impl

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.calendar.IRemoteDBProviderCalendar
import com.nick_sib.beauty_radar.model.provider_new.provider_db.IProviderRemoteDB
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository

class RemoteRepositoryImpl(
    private val remoteDB: IRemoteDBProviderCalendar,
    private val remoteDBBackend: IProviderRemoteDB
) :
    RemoteRepository<AppState> {

    override fun getData(): AppState {
        return AppState.Success(remoteDB.getListCalendarProfile())
    }

    override suspend fun checkUserInDB(uid: String): AppState {
        return AppState.Success(remoteDBBackend.getUserByUPN(uid))
    }


}