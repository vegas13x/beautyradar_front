package com.nick_sib.beauty_radar.model.provider_new.retrofit

import androidx.lifecycle.MutableLiveData
import javax.inject.Singleton

@Singleton
class BlockUserRepository {

    val blockUser = MutableLiveData<Event<Unit>>()

    fun block() = blockUser.postValue(Event(Unit))
}