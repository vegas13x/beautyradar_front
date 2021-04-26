package com.nick_sib.beauty_radar.model.provider_new.api

import com.nick_sib.beauty_radar.model.provider_new.repository.user.User
import com.nick_sib.beauty_radar.model.provider_new.repository.user.NewUserProfile
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.DELETE
import retrofit2.http.PUT

interface ApiService {

    @GET()
    fun createUser(@Query("user") user: User)

    @PUT()
    fun updateUser(@Query("user") user: User)

    @GET()
    fun getUserByUPN(@Query("user") upn: String): NewUserProfile

    @GET()
    fun getUserList(): NewUserProfile

    @DELETE
    fun deleteUser(@Query("user") upn: String)

}