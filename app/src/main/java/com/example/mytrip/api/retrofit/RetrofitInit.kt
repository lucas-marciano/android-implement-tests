/*
* Developed by: Inatel Competence Center
* Copyright 2019, Inalel
* All rights are reserved. Reproduction in whole or part is
* prohibited without the written consent of the copyright owner.
*/
package com.example.mytrip.api.retrofit

import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import com.example.mytrip.api.retrofit.service.TripService
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInit{
    private val URL_BASE = "http://192.168.20.13:8080/"
    private var retrofit: Retrofit

    init {
        val client: OkHttpClient = configureHttpClient()
        retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun configureHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    fun tripService(): TripService {
        return retrofit.create<TripService>(TripService::class.java)
    }
}