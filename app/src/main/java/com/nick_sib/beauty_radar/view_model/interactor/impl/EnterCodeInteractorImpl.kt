package com.nick_sib.beauty_radar.view_model.interactor.impl

import com.google.firebase.messaging.FirebaseMessaging
import com.nick_sib.beauty_radar.model.data.entites.UserMaster
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.user.MasterDTO
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.model.room.HistoryDataBase
import com.nick_sib.beauty_radar.model.room.RoomMaster
import com.nick_sib.beauty_radar.view.utils.TAG_DEBAG
import com.nick_sib.beauty_radar.view_model.interactor.core.EnterCodeInteractor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class EnterCodeInteractorImpl(private val remoteRepo: RemoteRepository<AppState>) :
    EnterCodeInteractor<AppState> {

    override suspend fun getUserByUPNFromDB(uid: String): AppState =
        remoteRepo.existUserByUPNFromDB(uid)

    override suspend fun updateUser(id: Long?, userDTO: UserDTO): AppState {
        return remoteRepo.updateUser(id, userDTO)
    }

    override suspend fun getToken(): String {
        return suspendCoroutine { res ->
            FirebaseMessaging.getInstance().token.addOnCompleteListener {
                if (it.isComplete) {
                    val fbToken = it.result.toString()
                    res.resume(fbToken)
                }
            }
        }
    }

    override suspend fun getUserInLocalDB() {
        var userMaster = RoomMaster("1","2","3","4")
    }

    override suspend fun existUserByUPNFromDB(uid: String): AppState {
        return remoteRepo.existUserByUPNFromDB(uid)}

}

