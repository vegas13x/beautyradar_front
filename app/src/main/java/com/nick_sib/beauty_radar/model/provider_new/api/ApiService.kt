package com.nick_sib.beauty_radar.model.provider_new.api

import com.nick_sib.beauty_radar.model.provider_new.repository.user.User
import com.nick_sib.beauty_radar.model.provider_new.repository.user.NewUserProfile
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("user")
    suspend fun createUserAsync(@Body user: User): Deferred<NewUserProfile>

    @PUT("user")
    suspend fun updateUserAsync(@Body user: User): Deferred<NewUserProfile>

    @GET("/user/{upn}")
    suspend fun getUserByUPNAsync(@Path("upn") upn: String): Deferred<NewUserProfile>

    @GET()
    suspend fun getUserListAsync(): Deferred<List<NewUserProfile>>

    @DELETE
    suspend fun deleteUserAsync(@Query("user") upn: String): Deferred<NewUserProfile>

}