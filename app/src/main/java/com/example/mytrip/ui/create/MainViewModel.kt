package com.example.mytrip.ui.create

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytrip.api.retrofit.client.ResponseListener
import com.example.mytrip.api.retrofit.client.TripWebClient
import com.example.mytrip.application.database
import com.example.mytrip.data.models.Trip
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    private val client: TripWebClient = TripWebClient()

    val distance: ObservableField<String> = ObservableField()
    val price: ObservableField<String> = ObservableField()
    val autonomy: ObservableField<String> = ObservableField()
    val results: ObservableField<String> = ObservableField()

    val errorInformation: MutableLiveData<String> = MutableLiveData()
    val savedInDataBase: MutableLiveData<Boolean> = MutableLiveData()
    val savedTrip: MutableLiveData<Trip> = MutableLiveData()

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var mainTripObj: Trip? = null

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        savedInDataBase.value = false
    }

    fun handleCalculateButtonClick() {
        handleCalculate()
    }

    fun handleSaveButtonClick() {
        if (mainTripObj != null) {
            uiScope.launch {
                mainTripObj?.let {
                    saveTrip(it)
                    mainTripObj = null
                    savedInDataBase.value = true
                }
            }
        } else {
            errorInformation.value = "Você precisa calcular antes de salvar"
        }
    }

    fun handleExportButtonClick() {
        if (mainTripObj != null) {
            mainTripObj?.let {
                exportTrip(it)
                mainTripObj = null
            }
        } else {
            errorInformation.value = "Você precisa calcular antes de salvar"
        }
    }

    fun handleCalculate() {
        if (isValid()) {
            try {
                val distance = distance.get().toString().toFloat()
                val price = price.get().toString().toFloat()
                val autonomy = autonomy.get().toString().toFloat()
                val result: Float = ((distance * price) / autonomy)
                results.set("Total: R$ $result")

                mainTripObj = Trip(
                    distance = distance.toDouble(),
                    price = price.toDouble(),
                    autonomy = autonomy.toDouble(),
                    result = result.toDouble()
                )
                clearFields()
            } catch (nfe: NumberFormatException) {
                errorInformation.value = "Por Favor informe valores válidos"
            }
        } else {
            errorInformation.value = "Por Favor informe valores válidos"
        }
    }

    fun exportTrip(trip: Trip) {
        client.save(trip, object : ResponseListener<Trip>{
            override fun success(response: Trip?) {
                savedTrip.value = trip
            }

            override fun failure(response: String?) {
                errorInformation.value = response
            }
        })
    }

    private suspend fun saveTrip(trip: Trip) = withContext(Dispatchers.Default) {
        database.tripDao()?.insert(trip)
    }

    fun isValid() = distance.get() != ""
            && price.get() != ""
            && autonomy.get() != ""
            && autonomy.get() != "0"

    private fun clearFields() {
        distance.set("")
        price.set("")
        autonomy.set("")
    }
}
