package com.nick_sib.beauty_radar.view_model.interactor.core

import com.nick_sib.beauty_radar.model.provider.repository.master.UserMasterProfile

interface ProfileInfoEditInteractor<S>: Interactor {

    suspend fun sendUserToDB(userMasterProfile: UserMasterProfile): S

    suspend fun getUserFromDB(): S

}
