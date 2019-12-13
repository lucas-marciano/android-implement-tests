/*
* Developed by: Inatel Competence Center
* Copyright 2019, Inalel
* All rights are reserved. Reproduction in whole or part is
* prohibited without the written consent of the copyright owner.
*/
package com.example.mytrip.data.daos

import androidx.room.*
import com.example.mytrip.data.models.Trip

@Dao
interface TripDao {
    @Query("SELECT * FROM trip")
    suspend fun loadAll(): List<Trip>

    @Query("SELECT * FROM trip WHERE id = (:id) LIMIT 1")
    suspend fun loadById(vararg id: Long): Trip

    @Insert
    suspend fun insert(items: Trip): Long

    @Delete
    suspend fun delete(items: Trip)

    @Update
    suspend fun update(items: Trip)
}