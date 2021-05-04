package com.nick_sib.beauty_radar.view_model.interactor.impl

import com.google.firebase.messaging.FirebaseMessaging
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.view_model.interactor.core.SignUpInteractor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class SignUpInteractorImpl(private val remoteRepo: RemoteRepository<AppState>) :
    SignUpInteractor<AppState> {

    override suspend fun createUser(UserDTO: UserDTO): AppState =
        remoteRepo.createUser(UserDTO)

    override suspend fun updateUser(UserDTO: UserDTO): AppState =
        remoteRepo.updateUser(UserDTO)

    override suspend fun getToken(): String {
        return suspendCoroutine { res ->
            FirebaseMessaging.getInstance().token.addOnCompleteListener {
                if (it.isComplete) {
                    var fbToken = it.result.toString()
                    res.resume(fbToken)
                }
            }
        }
    }





}
