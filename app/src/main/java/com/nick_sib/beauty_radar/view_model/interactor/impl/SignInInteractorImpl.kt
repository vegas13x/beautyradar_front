package com.nick_sib.beauty_radar.view_model.interactor.impl

import com.google.firebase.messaging.FirebaseMessaging
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.view_model.interactor.core.SignInInteractor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SignInInteractorImpl(private val remoteRepo: RemoteRepository<AppState>) :
    SignInInteractor<AppState> {

    override suspend fun createUser(UserDTO: UserDTO): AppState =
        remoteRepo.createUser(UserDTO)

    override suspend fun updateUser(UserDTO: Long?, userDTO: UserDTO): AppState =
        remoteRepo.updateUser(UserDTO,userDTO)

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
