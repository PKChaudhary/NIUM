package com.abstractlearning.tutorials.nium.data.api

import com.abstractlearning.tutorials.nium.data.model.CountryDetails
import retrofit2.http.GET

interface ApiService {

    @GET("all")
    suspend fun getCountryDetails(): List<CountryDetails>

}