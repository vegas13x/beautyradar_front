package com.nick_sib.beauty_radar.model.provider.api

import com.nick_sib.beauty_radar.model.provider.repository.user.UserDBCheck
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.model.provider.repository.user.UserResponse
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("user/")
    suspend fun createUserAsync(@Body userDTO: UserDTO): UserResponse

    @GET("user/{id}")
    suspend fun getUserByUPNAsync(@Path("id") id: String): UserResponse

    @PUT("user/{id}")
    suspend fun updateUserAsync(@Path("id") id: String): UserResponse

    @GET("user/{upn}/exists")
    suspend fun existUserByUPNAsync(@Path("upn") upn: String): UserDBCheck

//    @GET()
//    suspend fun getUserListAsync(): List<UserResponse>
//
//    @DELETE
//    suspend fun deleteUserAsync(@Query("user") upn: String): UserResponse

}