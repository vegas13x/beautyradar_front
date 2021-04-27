package com.nick_sib.beauty_radar.model.provider_new.retrofit

import com.google.android.datatransport.runtime.dagger.Reusable
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nick_sib.beauty_radar.model.provider_new.api.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitImplementation {

    fun provideRequestApi(
        retrofitBuilder: Retrofit.Builder,
        okHttpClientBuilder: OkHttpClient.Builder,
//        appVersionInterceptor: AppVersionInterceptor,
//        accessTokenInterceptor: AccessTokenInterceptor,
        errorInterceptor: ErrorInterceptor
    ): ApiService {
        val okHttpClient = okHttpClientBuilder.apply {
//            addInterceptor(appVersionInterceptor)
//            addInterceptor(accessTokenInterceptor)
            addInterceptor(errorInterceptor)
        }.build()

        return retrofitBuilder.client(okHttpClient).build().create(ApiService::class.java)
    }


    private fun createRetrofit(): Retrofit.Builder = Retrofit.Builder().apply {
        baseUrl(BASE_URL_LOCATIONS)
        addConverterFactory(MoshiConverterFactory.create().asLenient())
        addCallAdapterFactory(CoroutineCallAdapterFactory())
    }

//    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
//        val httpClient = OkHttpClient.Builder()
//        httpClient.addInterceptor(interceptor)
//        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//        return httpClient.build()
//    }


    companion object {
        private const val BASE_URL_LOCATIONS = "http://109.248.203.238:9999/frontgateway/"
    }
}