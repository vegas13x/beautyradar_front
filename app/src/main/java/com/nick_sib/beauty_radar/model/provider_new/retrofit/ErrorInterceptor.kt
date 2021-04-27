package com.nick_sib.beauty_radar.model.provider_new.retrofit

import okhttp3.Interceptor
import javax.inject.Singleton
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

@Singleton
class ErrorInterceptor (
    private val blockUserRepository: BlockUserRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val response = chain.proceed(originalRequest)
        val body = response.body
        val bodyRawString = body?.string() ?: ""

        if (response.code != 0) {
            blockUserRepository.block()
        }

        return response.newBuilder()
            .body(bodyRawString.toResponseBody(body?.contentType()))
            .build()
    }
}