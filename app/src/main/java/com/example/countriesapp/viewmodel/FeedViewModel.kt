package com.example.countriesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countriesapp.model.Country

class FeedViewModel : ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryErrorMessage = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {

        val country = Country("Turkey", "Asia", "Ankara", "TRY", "Turkish", "www.ss.com")
        val country2 = Country("France", "Europe", "Paris", "EUR", "French", "www.ss.com")
        val country3 = Country("Germany", "Europe", "Berlin", "EUR", "German", "www.ss.com")

        val countryList = arrayListOf<Country>(country, country2, country3)

        countries.value = countryList
        countryErrorMessage.value = false
        countryLoading.value = false
    }
}