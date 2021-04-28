package com.nick_sib.beauty_radar.model.provider.api

import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.model.provider.repository.user.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("user")
    suspend fun createUserAsync(@Body UserDTO: UserDTO): Deferred<UserResponse>

    @PUT("user")
    suspend fun updateUserAsync(@Body UserDTO: UserDTO): Deferred<UserResponse>

    @GET("user/{upn}")
    suspend fun getUserByUPNAsync(@Path("upn") upn: String): Deferred<UserResponse>

    @GET()
    suspend fun getUserListAsync(): Deferred<List<UserResponse>>

    @DELETE
    suspend fun deleteUserAsync(@Query("user") upn: String): Deferred<UserResponse>

}