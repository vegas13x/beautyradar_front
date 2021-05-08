package com.nick_sib.beauty_radar.view_model.interactor.core

import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO

interface EnterCodeInteractor<S> : Interactor {
    suspend fun existUserByUPNFromDB(uid: String): S
}