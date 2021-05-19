package com.nick_sib.beauty_radar.model.entity

import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.master.UserMasterProfile

class SingletonPutImpl: ISingletonPut {

    override fun putInSingleton(userMasterProfile: UserMasterProfile) {
        UserMasterProfileSingl.name = userMasterProfile.name
        UserMasterProfileSingl.surname = userMasterProfile.surname
        UserMasterProfileSingl.address = userMasterProfile.address
        UserMasterProfileSingl.phone = userMasterProfile.phone
        UserMasterProfileSingl.birthdayDate = userMasterProfile.dateBirthday
        UserMasterProfileSingl.aboutUrSelf = userMasterProfile.aboutUrself
    }

    override fun getFromSingleton(): AppState {
        var userMasterProfile = UserMasterProfile()
        userMasterProfile.name = UserMasterProfileSingl.name
        userMasterProfile.surname = UserMasterProfileSingl.surname
        userMasterProfile.address = UserMasterProfileSingl.address
        userMasterProfile.phone = UserMasterProfileSingl.phone
        userMasterProfile.dateBirthday = UserMasterProfileSingl.birthdayDate
        userMasterProfile.aboutUrself = UserMasterProfileSingl.aboutUrSelf
        return AppState.Success(userMasterProfile)
    }


}