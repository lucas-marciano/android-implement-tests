package com.example.mytrip.ui.create

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mytrip.R
import com.example.mytrip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(
            MainViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).apply {
            this.lifecycleOwner = this@MainActivity
            this.viewModel = mainViewModel
        }

        setupObservables()
    }

    private fun setupObservables() {
        mainViewModel.errorInformation.observe(this, Observer { value ->
            if (value != null) {
                Toast.makeText(application, value.toString(), Toast.LENGTH_LONG).show()
            }
        })

        mainViewModel.savedInDataBase.observe(this, Observer { value ->
            if (value != null && value) {
                Toast.makeText(application, "Item salvo no banco de dados", Toast.LENGTH_LONG)
                    .show()
            }
        })

        mainViewModel.savedTrip.observe(this, Observer { value ->
            value?.let {
                Toast.makeText(application, "Item salvo no servidor", Toast.LENGTH_LONG).show()
            }
        })
    }
}
