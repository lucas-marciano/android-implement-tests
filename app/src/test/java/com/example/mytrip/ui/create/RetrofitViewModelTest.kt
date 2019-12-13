package com.example.mytrip.ui.create

import com.example.mytrip.api.SenderTrip
import com.example.mytrip.api.retrofit.client.ResponseListener
import com.example.mytrip.api.retrofit.client.TripWebClient
import com.example.mytrip.data.models.Trip
import com.example.mytrip.factories.TripUnitFactory
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RetrofitViewModelTest {

    @Mock
    private lateinit var client: TripWebClient

    @Mock
    private lateinit var listener: SenderTrip.ProcessListener

    @Mock
    private lateinit var trip: Trip

    @Test
    fun `should save a item in server`() = runBlocking {
        trip = TripUnitFactory.create()[0]

        val sender = SenderTrip(
            client = client,
            listener = listener
        )

        sender.save(trip)

        verify(client, never()).save(
            any(Trip::class.java),
            any(ResponseListener::class.java) as ResponseListener<Trip>
        )

    }

}
