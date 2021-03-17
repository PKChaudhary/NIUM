package com.abstractlearning.tutorials.nium.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getDetails() = apiService.getCountryDetails()
}