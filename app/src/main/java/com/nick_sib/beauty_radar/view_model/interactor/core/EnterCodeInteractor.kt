package com.nick_sib.beauty_radar.view_model.interactor.core

import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO

interface EnterCodeInteractor<S> : Interactor {
    suspend fun getUserByUPNFromDB(uid: String): S

    suspend fun updateUser(userDTO: UserDTO): S

    suspend fun getToken(): String
}