package com.nick_sib.beauty_radar.model.provider_new

import com.nick_sib.beauty_radar.model.provider_new.api.ApiService
import com.nick_sib.beauty_radar.model.provider_new.repository.user.User

abstract class UserRegistryProvider(private val apiRequests: ApiService) {

    suspend fun registerNewUser(
        userData: User
    ): Boolean {
        val res = apiRequests.createUser(userData).body
        return res == userData.copy(id = res?.id)
    }
}