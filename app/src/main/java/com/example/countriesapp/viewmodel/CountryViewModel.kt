package com.example.countriesapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countriesapp.model.Country
import com.example.countriesapp.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application) : BaseViewModel(application) {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(id: Int) {
        launch {
            val countryDao = CountryDatabase(getApplication()).countryDao()
            val country = countryDao.getCountry(id)
            countryLiveData.value = country
        }
    }
}