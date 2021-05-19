package com.nick_sib.beauty_radar.model.repository.impl

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.entity.ISingletonPut
import com.nick_sib.beauty_radar.model.provider.calendar.IRemoteDBProviderCalendar
import com.nick_sib.beauty_radar.model.provider.provider_db.IProviderRemoteDB
import com.nick_sib.beauty_radar.model.provider.repository.master.UserMasterProfile
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.model.provider.service.IRemoteDBProviderService
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository

class RemoteRepositoryImpl(
    private val remoteDB: IRemoteDBProviderCalendar,
    private val remoteDBBackend: IProviderRemoteDB,
    private val remoteDBProviderService: IRemoteDBProviderService,
    private val remoteSingleton: ISingletonPut
) :
    RemoteRepository<AppState> {

    override suspend fun getService(): AppState =
        AppState.Success(remoteDBProviderService.getListServiceProfile())

    override fun getCalendar(): AppState =
        AppState.Success(remoteDB.getListCalendarProfile())

    override suspend fun getUserByUPNFromDB(uid: String): AppState =
        AppState.Success(remoteDBBackend.getUserByUPN(uid).body)

    override suspend fun existUserByUPNFromDB(uid: String): AppState =
        AppState.Success(remoteDBBackend.existUserByUPN(uid).body)

    override suspend fun createUser(UserDTO: UserDTO): AppState =
        AppState.Success(remoteDBBackend.createUser(UserDTO))

    override suspend fun updateUser(id: Long?, userDTO: UserDTO): AppState =
        AppState.Success(remoteDBBackend.updateUser(id, userDTO))

    override suspend fun sendUserToDB(userMasterProfile: UserMasterProfile): AppState =
        AppState.Success(remoteSingleton.putInSingleton(userMasterProfile))

    override suspend fun getUserFromDB(): AppState =
        remoteSingleton.getFromSingleton()


}