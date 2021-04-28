package com.nick_sib.beauty_radar.model.repository.impl

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.calendar.IRemoteDBProviderCalendar
import com.nick_sib.beauty_radar.model.provider.provider_db.IProviderRemoteDB
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository

class RemoteRepositoryImpl(
    private val remoteDB: IRemoteDBProviderCalendar,
    private val remoteDBBackend: IProviderRemoteDB
) :
    RemoteRepository<AppState> {

    override fun getData(): AppState {
        return AppState.Success(remoteDB.getListCalendarProfile())
    }

    override suspend fun getUserByUPNFromDB(uid: String): AppState {
        val tt = remoteDBBackend.getUserByUPN(uid)
        return AppState.Success(tt)
    }

    override suspend fun createUser(UserDTO: UserDTO): AppState {
        return AppState.Success(remoteDBBackend.createUser(UserDTO))
    }

    override suspend fun updateUser(UserDTO: UserDTO): AppState {
        return AppState.Success(remoteDBBackend.updateUser(UserDTO))
    }

    override suspend fun getUserList(): AppState {
        return AppState.Success(remoteDBBackend.getUserList())
    }

    override suspend fun deleteUser(uid: String): AppState {
        return AppState.Success(remoteDBBackend.deleteUser(uid))
    }


}