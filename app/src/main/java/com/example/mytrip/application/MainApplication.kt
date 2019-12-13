/*
* Developed by: Inatel Competence Center
* Copyright 2019, Inalel
* All rights are reserved. Reproduction in whole or part is
* prohibited without the written consent of the copyright owner.
*/
package com.example.mytrip.application

import android.app.Application
import androidx.room.Room
import com.example.mytrip.data.AppDatabase

val database: AppDatabase by lazy {
    MainApplication.database!!
}

class MainApplication : Application() {

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()

        database = Room
            .databaseBuilder(
                this@MainApplication,
                AppDatabase::class.java, "database-test"
            ).build()
    }
}