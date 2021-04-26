package com.nick_sib.beauty_radar.model.provider_new.api

import com.nick_sib.beauty_radar.model.provider_new.repository.user.User
import com.nick_sib.beauty_radar.model.provider_new.repository.user.NewUserProfile
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("user")
    fun createUser(@Body user: User): Deferred<NewUserProfile>

    @PUT("user")
    fun updateUser(@Body user: User): Deferred<NewUserProfile>

    @GET("/user/{upn}")
    suspend fun getUserByUPN(@Path("upn") upn: String): Deferred<NewUserProfile>

    @GET()
    suspend fun getUserList(): Deferred<List<NewUserProfile>>

    @DELETE
    suspend fun deleteUser(@Query("user") upn: String): Deferred<NewUserProfile>

}