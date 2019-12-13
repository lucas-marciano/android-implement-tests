/*
* Developed by: Inatel Competence Center
* Copyright 2019, Inalel
* All rights are reserved. Reproduction in whole or part is
* prohibited without the written consent of the copyright owner.
*/
package com.example.mytrip.api

import android.util.Log
import com.example.mytrip.api.retrofit.client.TripWebClient
import com.example.mytrip.api.retrofit.client.ResponseListener
import com.example.mytrip.data.models.Trip

class SenderTrip(
    private val client: TripWebClient,
    private val listener: ProcessListener
) {

    fun save(trip: Trip) {
        client.save(trip, object : ResponseListener<Trip> {
            override fun success(response: Trip?) {
                listener.process(response)
            }

            override fun failure(response: String?) {
                listener.processError(response.toString())
            }
        })
    }

    fun getById(id: Int) {
        client.getById(id, object : ResponseListener<Trip> {
            override fun success(response: Trip?) {
                response?.let {
                    listener.process(response)
                }
            }

            override fun failure(response: String?) {
                Log.e("SenderTrip", response.toString())
                listener.processError(response.toString())
            }
        })
    }

    fun getAll() {
        client.getAll(object : ResponseListener<List<Trip>> {
            override fun success(response: List<Trip>?) {
                response?.let {
                    listener.processList(response)
                }
            }

            override fun failure(response: String?) {
                Log.e("SenderTrip", response.toString())
                listener.processError(response.toString())
            }
        })
    }

    interface ProcessListener {
        fun process(trip: Trip?)
        fun processList(trips: List<Trip>)
        fun processError(message: String)
    }
}