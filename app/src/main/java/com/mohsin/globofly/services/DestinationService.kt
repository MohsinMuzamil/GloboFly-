// This interface defines the API endpoints for Retrofit to communicate with your backend server.

package com.mohsin.globofly.services

import com.mohsin.globofly.models.Destination
import  retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// Each function corresponds to a REST API operation for the "destination" resource.
interface DestinationService {

    // Get the list of all destinations from the backend.
    @GET("/destination")
    fun getDestinationList(): Call<List<Destination>>

    // Add a new destination by sending a POST request with the Destination object in the request body.
    @POST("/destination")
    fun addDestination(@Body destination: Destination): Call<Destination>

    // Update an existing destination by ID. Sends a PUT request with the updated Destination object.
    @PUT("/destination/{id}")
    fun updateDestination(@Path("id") id: Int, @Body destination: Destination): Call<Destination>

    // Delete a destination by ID. Sends a DELETE request and expects no response body (Void).
    @DELETE("/destination/{id}")
    fun deleteDestination(@Path("id") id: Int): Call<Void>

}