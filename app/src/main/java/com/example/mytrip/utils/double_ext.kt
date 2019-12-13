/*
* Developed by: Inatel Competence Center
* Copyright 2019, Inalel
* All rights are reserved. Reproduction in whole or part is
* prohibited without the written consent of the copyright owner.
*/
package com.example.mytrip.utils

import kotlin.math.roundToInt

fun Double.convertTwoDecimalNumbers(): Double {
    return (this * 10.0).roundToInt() / 10.0
}