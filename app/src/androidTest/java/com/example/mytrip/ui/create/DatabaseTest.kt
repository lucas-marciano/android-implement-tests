package com.example.mytrip.ui.create

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mytrip.TripFactory
import com.example.mytrip.TripFactory.updateFakeData
import com.example.mytrip.data.AppDatabase
import com.example.mytrip.data.daos.TripDao
import com.example.mytrip.data.models.Trip
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var mDatabase: AppDatabase
    private lateinit var mTripDao: TripDao

    @Before
    @Throws(IOException::class)
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        mDatabase = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()

        mDatabase.tripDao()?.let { dao ->
            mTripDao = dao
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDataBase() {
        mDatabase.close()
    }

    @Test
    @Throws(IOException::class)
    fun whenSaveAndListTripsInDataBase() = runBlocking {
        val fakeTrip: Trip = TripFactory.create()[0]
        val id = mTripDao.insert(fakeTrip)
        fakeTrip.id = id
        val trips = mTripDao.loadAll()
        assertEquals(listOf(fakeTrip), trips)
    }

    @Test
    @Throws(IOException::class)
    fun whenDeleteTripInDataBase() = runBlocking {
        val tripsFake = TripFactory.create(10)
        for (item in tripsFake) {
            mTripDao.insert(item)
        }

        val initialTripsList = mTripDao.loadAll()
        mTripDao.delete(initialTripsList[0])

        val finalTripsList = mTripDao.loadAll()

        assertEquals((initialTripsList.size - 1), finalTripsList.size)
    }

    @Test
    @Throws(IOException::class)
    fun whenSelectTripByIdInDataBase() = runBlocking {
        val fakeTrip: Trip = TripFactory.create()[0]
        val id = mTripDao.insert(fakeTrip)
        fakeTrip.id = id
        val trip = mTripDao.loadById(id)

        assertEquals(fakeTrip, trip)
    }

    @Test
    @Throws(IOException::class)
    fun whenUpdateTripInDataBase() = runBlocking {
        val tripsFake = TripFactory.create(10)
        for (item in tripsFake)
            mTripDao.insert(item)

        val trips: List<Trip> = mTripDao.loadAll()
        var tripUpdated: Trip = trips[0]
        tripUpdated = updateFakeData(tripUpdated, distance = 4000.0)
        mTripDao.update(tripUpdated)

        val newTrip = mTripDao.loadById(tripUpdated.id)

        assertEquals(newTrip, tripUpdated)
    }
}