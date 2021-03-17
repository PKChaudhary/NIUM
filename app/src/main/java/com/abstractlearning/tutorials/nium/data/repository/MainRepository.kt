package com.abstractlearning.tutorials.nium.data.repository

import com.abstractlearning.tutorials.nium.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getDetails() = apiHelper.getDetails()
}