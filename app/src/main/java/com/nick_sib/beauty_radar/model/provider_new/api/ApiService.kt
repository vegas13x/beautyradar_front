package com.nick_sib.beauty_radar.model.provider_new.api

import com.nick_sib.beauty_radar.model.provider_new.repository.user.User
import com.nick_sib.beauty_radar.model.provider_new.repository.user.NewUserProfile
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("user")
    fun createUser(@Body user: User)

    @PUT()
    fun updateUser(@Query("user") user: User)

    @GET()
    fun getUserByUPN(@Query("user") upn: String): NewUserProfile

    @GET()
    fun getUserList(): NewUserProfile

    @DELETE
    fun deleteUser(@Query("user") upn: String)

}