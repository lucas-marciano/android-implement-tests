/*
* Developed by: Inatel Competence Center
* Copyright 2019, Inalel
* All rights are reserved. Reproduction in whole or part is
* prohibited without the written consent of the copyright owner.
*/
package com.example.mytrip.utils

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}