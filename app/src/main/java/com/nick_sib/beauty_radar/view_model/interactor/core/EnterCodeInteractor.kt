package com.nick_sib.beauty_radar.view_model.interactor.core

interface EnterCodeInteractor<S> : Interactor {
    suspend fun existUserByUPNFromDB(uid: String): S
    suspend fun getUserByUPNFromDB(uid: String): S

    suspend fun updateUser(id: Int?): S

    suspend fun getToken(): String
}