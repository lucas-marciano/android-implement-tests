/*
* Developed by: Inatel Competence Center
* Copyright 2019, Inalel
* All rights are reserved. Reproduction in whole or part is
* prohibited without the written consent of the copyright owner.
*/
package com.example.mytrip.api.retrofit.client

import com.example.mytrip.api.retrofit.RetrofitInit
import com.example.mytrip.api.retrofit.service.TripService
import com.example.mytrip.data.models.Trip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TripWebClient {
    private var service: TripService = RetrofitInit().tripService()

    fun save(trip: Trip, listener: ResponseListener<Trip>) {
        val call = service.save(trip)
        call?.enqueue(object : Callback<Trip?> {
            override fun onResponse(call: Call<Trip?>?, response: Response<Trip?>) {
                if (response.isSuccessful) {
                    listener.success(response.body())
                }
            }

            override fun onFailure(call: Call<Trip?>?, t: Throwable) {
                listener.failure(t.message)
            }
        })
    }

    fun getById(id: Int, listener: ResponseListener<Trip>) {
        val call = service.getById(id)
        call?.enqueue(object : Callback<Trip?> {
            override fun onResponse(call: Call<Trip?>?, response: Response<Trip?>) {
                if (response.isSuccessful && response.body() != null) {
                    listener.success(response.body())
                }
            }

            override fun onFailure(call: Call<Trip?>?, t: Throwable) {
                listener.failure(t.message)
            }
        })
    }

    fun getAll(listener: ResponseListener<List<Trip>>) {
        val call = service.getAll()
        call?.enqueue(object : Callback<List<Trip>?> {
            override fun onResponse(call: Call<List<Trip>?>?, response: Response<List<Trip>?>) {
                if (response.isSuccessful && response.body() != null) {
                    listener.success(response.body())
                }
            }

            override fun onFailure(call: Call<List<Trip>?>?, t: Throwable) {
                listener.failure(t.message)
            }
        })
    }

}