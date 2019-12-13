/*
* Developed by: Inatel Competence Center
* Copyright 2019, Inalel
* All rights are reserved. Reproduction in whole or part is
* prohibited without the written consent of the copyright owner.
*/
package com.example.mytrip.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytrip.data.daos.TripDao
import com.example.mytrip.data.models.Trip

@Database(entities = [Trip::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao?
}