package com.nick_sib.beauty_radar.view_model.interactor.core



interface EnterCodeInteractor<S> : Interactor {
    suspend fun getUserByUPNFromDB(uid: String): S
}