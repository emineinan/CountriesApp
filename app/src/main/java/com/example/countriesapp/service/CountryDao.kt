package com.example.countriesapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.countriesapp.model.Country

@Dao
interface CountryDao {
    @Insert
    suspend fun insertAll(vararg countries: Country): List<Long>

    @Query("SELECT * FROM country_table")
    suspend fun getAllCountries(): List<Country>

    @Query("SELECT * FROM country_table WHERE id = :countryId")
    suspend fun getCountry(countryId: Int): Country

    @Query("DELETE FROM country_table")
    suspend fun deleteAllCountries()
}