/*
* Developed by: Inatel Competence Center
* Copyright 2019, Inalel
* All rights are reserved. Reproduction in whole or part is
* prohibited without the written consent of the copyright owner.
*/
package com.example.mytrip.api.retrofit.service

import com.example.mytrip.data.models.Trip
import retrofit2.Call
import retrofit2.http.*

interface TripService {

    @POST("trips")
    fun save(@Body trip: Trip): Call<Trip>?

    @GET("trips/{id}")
    fun getById(@Path("id") id: Int): Call<Trip>?

    @GET("trips")
    fun getAll(): Call<List<Trip>>?
}