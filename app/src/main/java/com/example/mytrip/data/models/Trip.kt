/*
* Developed by: Inatel Competence Center
* Copyright 2019, Inalel
* All rights are reserved. Reproduction in whole or part is
* prohibited without the written consent of the copyright owner.
*/
package com.example.mytrip.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Trip(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var distance: Double,
    var price: Double,
    var autonomy: Double,
    var result: Double,
    var created_at: String = "",
    var updated_at: String = ""
)