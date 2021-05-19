package com.nick_sib.beauty_radar.model.entity

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.master.UserMasterProfile

interface ISingletonPut {

    fun putInSingleton(userMasterProfile: UserMasterProfile)

    fun getFromSingleton(): AppState

}