package com.nick_sib.beauty_radar.model.repository.impl

import android.util.Log
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.calendar.IRemoteDBProviderCalendar
import com.nick_sib.beauty_radar.model.provider_new.provider_db.IProviderRemoteDB
import com.nick_sib.beauty_radar.model.provider_new.repository.user.UserDTO
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.view.utils.TAG_DEBAG

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
        val tt = remoteDBBackend.getUserByUPN(uid)
        Log.d(TAG_DEBAG, "getUserByUPNFromDB: $tt")
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