/*
* Developed by: Inatel Competence Center
* Copyright 2019, Inalel
* All rights are reserved. Reproduction in whole or part is
* prohibited without the written consent of the copyright owner.
*/
package com.example.mytrip.api.retrofit.client


interface ResponseListener<T> {
    fun success(response: T?)

    fun failure(response: String?)
}