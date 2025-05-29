package com.mohsin.globofly.services

import com.mohsin.globofly.models.Destination
import  retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface DestinationService {

    @GET("/destination")
    fun getDestinationList(): Call<List<Destination>>

    @POST("/destination")
    fun addDestination(@Body destination: Destination): Call<Destination>

    @PUT("/destination/{id}")
    fun updateDestination(@Path("id") id: Int, @Body destination: Destination): Call<Destination>

    @DELETE("/destination/{id}")
    fun deleteDestination(@Path("id") id: Int): Call<Void>

}