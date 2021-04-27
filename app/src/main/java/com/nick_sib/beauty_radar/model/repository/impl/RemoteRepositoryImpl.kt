package com.nick_sib.beauty_radar.model.repository.impl

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.calendar.IRemoteDBProviderCalendar
import com.nick_sib.beauty_radar.model.provider_new.provider_db.IProviderRemoteDB
import com.nick_sib.beauty_radar.model.provider_new.repository.user.User
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository

class RemoteRepositoryImpl(
    private val remoteDB: IRemoteDBProviderCalendar,
    private val remoteDBBackend: IProviderRemoteDB
) :
    RemoteRepository<AppState> {

    // Работа со старой DB
    override fun getData(): AppState {
        return AppState.Success(remoteDB.getListCalendarProfile())
    }

    // Новая DB
    override suspend fun getUserByUPNFromDB(uid: String): AppState {
        return AppState.Success(remoteDBBackend.getUserByUPN(uid))
    }

    override suspend fun createUser(user: User): AppState {
        return AppState.Success(remoteDBBackend.createUser(user))
    }

    override suspend fun updateUser(user: User): AppState {
        return AppState.Success(remoteDBBackend.updateUser(user))
    }

    override suspend fun getUserList(): AppState {
        return AppState.Success(remoteDBBackend.getUserList())
    }

    override suspend fun deleteUser(uid: String): AppState {
        return AppState.Success(remoteDBBackend.deleteUser(uid))
    }


}