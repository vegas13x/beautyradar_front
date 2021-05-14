package com.nick_sib.beauty_radar.model.provider.api

import com.nick_sib.beauty_radar.model.provider.repository.user.MasterDTO
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDBCheck
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.model.provider.repository.user.UserResponse
import retrofit2.http.*

interface ApiService {

    @POST("user/")
    suspend fun createUserAsync(@Body userDTO: UserDTO): UserResponse

    @GET("user/upn/{upn}")
    suspend fun getUserByUPNAsync(@Path("upn") upn: String): UserResponse

    @PUT("user/{id}")
    suspend fun updateUserAsync(@Path("id") id: Long?,@Body userDTO: UserDTO): UserResponse

    @GET("user/{upn}/exists")
    suspend fun existUserByUPNAsync(@Path("upn") upn: String): UserDBCheck

    @POST("master/")
    suspend fun createMasterAsync(@Body masterDTO: MasterDTO): UserResponse

//    @GET()
//    suspend fun getUserListAsync(): List<UserResponse>
//
//    @DELETE
//    suspend fun deleteUserAsync(@Query("user") upn: String): UserResponse

}