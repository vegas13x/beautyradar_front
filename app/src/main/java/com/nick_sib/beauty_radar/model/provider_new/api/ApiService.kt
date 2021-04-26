package com.nick_sib.beauty_radar.model.provider_new.api

import com.nick_sib.beauty_radar.model.provider_new.repository.user.User
import com.nick_sib.beauty_radar.model.provider_new.repository.user.NewUserProfile
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.DELETE
import retrofit2.http.PUT

interface ApiService {

    @GET("")
    fun createUser(@Query("user") user: List<User>)

    @PUT("")
    fun updateUser(@Query("user") user: List<User>)

    @GET("frontgateway/user/{upn}")
    fun getUserByUID(@Path("upn") upn: String): Call<Base>

    @GET("user")
    fun getUserList(): NewUserProfile

    @DELETE
    fun deleteUser(@Query("user") upn: String)

}