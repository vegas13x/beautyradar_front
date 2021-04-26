package com.nick_sib.beauty_radar.model.provider_new.api

import com.nick_sib.beauty_radar.model.provider_new.repository.user.User
import com.nick_sib.beauty_radar.model.provider_new.repository.user.NewUserProfile
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("user")
    fun createUser(@Body user: User)

    @PUT("user")
    fun updateUser(@Body user: User)

    @GET("/user/{upn}")
    suspend fun getUserByUPN(@Path("upn") upn: String): NewUserProfile

    @GET()
    fun getUserList(): NewUserProfile

    @DELETE
    fun deleteUser(@Query("user") upn: String)

}