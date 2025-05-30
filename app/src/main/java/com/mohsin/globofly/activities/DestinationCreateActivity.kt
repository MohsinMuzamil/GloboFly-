// This screen lets users enter details for a new destination and sends them to your backend API using Retrofit. If successful, it closes the screen.

package com.mohsin.globofly.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mohsin.globoFly.databinding.ActivityDestinyCreateBinding
import com.mohsin.globofly.models.Destination
import com.mohsin.globofly.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.mohsin.globofly.services.DestinationService



class DestinationCreateActivity : AppCompatActivity() {

	// This variable will let you access your layout's views easily
	private lateinit var binding: ActivityDestinyCreateBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		// Set up view binding to easily access views from the layout
		binding = ActivityDestinyCreateBinding.inflate(layoutInflater)
		setContentView(binding.root)

		// Set up the toolbar (the top bar in the app)
		setSupportActionBar(binding.toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		// When the Add button is clicked
		binding.btnAdd.setOnClickListener {
			// Create a Destination object with the data entered by the user
			val newDestination = Destination().apply {
				city = binding.etCity.text.toString()
				description = binding.etDescription.text.toString()
				country = binding.etCountry.text.toString()
			}

			// Build the network service (Retrofit) to talk to the backend API
			val service = ServiceBuilder.buildService(DestinationService::class.java)
			// Call the API to add a new destination
			val call = service.addDestination(newDestination)

			// Send the request in the background
			call.enqueue(object : Callback<Destination> {
				// If there is a failure (e.g., no internet)
				override fun onFailure(call: Call<Destination>, t: Throwable) {
					Toast.makeText(this@DestinationCreateActivity, "Failed to create: ${t.message}", Toast.LENGTH_LONG).show()
					t.printStackTrace()
				}
				// If the server responds
				override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
					if (response.isSuccessful) {
						// Show a message to the user that creation was successful
						Toast.makeText(this@DestinationCreateActivity, "Created successfully!", Toast.LENGTH_SHORT).show()
						finish()    // Close this screen
					} else {
						Toast.makeText(this@DestinationCreateActivity, "Failed: ${response.code()} - ${response.message()}", Toast.LENGTH_LONG).show()
					}
				}

			})
		 }
	}
}


