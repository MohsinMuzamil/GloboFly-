// This object is responsible for creating and configuring Retrofit service instances

package com.mohsin.globofly.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// used to communicate with your backend API.
object ServiceBuilder {


    // The base URL of your backend API (hosted on Railway)
    private const val URL = "https://globofly-production.up.railway.app/"

    // OkHttpClient is used by Retrofit for HTTP requests.
    private val okHttp = OkHttpClient.Builder()

    // Retrofit.Builder is used to configure the Retrofit instance
    // - Sets base URL
    // - Uses Gson for JSON parsing
    // - Attaches the OkHttpClient
    private val builder = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    // The Retrofit instance used to create service interfaces (APIs)
    private val retrofit: Retrofit = builder.build()

    // This function creates and returns a Retrofit service interface (API client)
    // Usage: ServiceBuilder.buildService(YourService::class.java)
    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}


