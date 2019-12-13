package com.example.mytrip

import com.example.mytrip.data.models.Trip
import com.example.mytrip.utils.convertTwoDecimalNumbers
import kotlin.random.Random

object TripFactory {

    fun create(total: Int = 0): List<Trip> {
        val trips: MutableList<Trip> = mutableListOf()
        for (index in 0..total) {

            val distance = Random.nextDouble(1.0, 1000.0).convertTwoDecimalNumbers()
            val price = Random.nextDouble(1.0, 100.0).convertTwoDecimalNumbers()
            val autonomy = Random.nextDouble(1.0, 100.0).convertTwoDecimalNumbers()

            val trip = Trip(
                distance = distance,
                price = price,
                autonomy = autonomy,
                result = ((distance * price) / autonomy).convertTwoDecimalNumbers()
            )

            trips.add(trip)
        }

        return trips
    }

    fun updateFakeData(
        trip: Trip,
        distance: Double? = null,
        price: Double? = null,
        autonomy: Double? = null
    ): Trip {
        distance?.let { trip.distance = distance }

        price?.let { trip.price = price }

        autonomy?.let { trip.autonomy = autonomy }

        trip.result = ((trip.distance * trip.price) / trip.autonomy).convertTwoDecimalNumbers()

        return trip
    }
}
