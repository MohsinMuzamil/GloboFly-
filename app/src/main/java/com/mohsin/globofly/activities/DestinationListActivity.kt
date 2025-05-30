// This Activity displays a list of all travel destinations.

package com.mohsin.globofly.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mohsin.globoFly.databinding.ActivityDestinyListBinding
import com.mohsin.globofly.helpers.DestinationAdapter
import com.mohsin.globofly.models.Destination
import com.mohsin.globofly.services.DestinationService
import com.mohsin.globofly.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// It fetches the destination list from your backend API using Retrofit
// and displays them using a RecyclerView with DestinationAdapter.
class DestinationListActivity : AppCompatActivity() {

	// View binding variable for easy access to layout views
	private lateinit var binding: ActivityDestinyListBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		// Inflate the layout using view binding
		binding = ActivityDestinyListBinding.inflate(layoutInflater)
		setContentView(binding.root)

		// Set up the toolbar at the top of the screen
		setSupportActionBar(binding.toolbar)
		binding.toolbar.title = title

		// Set up the Floating Action Button (+ button) to open the create destination screen
		binding.fab.setOnClickListener {
			val intent = Intent(this@DestinationListActivity, DestinationCreateActivity::class.java)
			startActivity(intent)
		}
	}

	// This function runs every time the screen becomes visible
	// It refreshes the list of destinations, so the user always sees the latest data
	override fun onResume() {
		super.onResume()
		loadDestinations()
	}

	// This function fetches the list of destinations from the backend API using Retrofit
	private fun loadDestinations() {

		// Create a Retrofit service for the API
		val destinationService: DestinationService = ServiceBuilder.buildService(DestinationService::class.java)

		// Prepare the GET request to fetch all destinations
		val requestCall: Call<List<Destination>> = destinationService.getDestinationList()

		// Make the API request asynchronously
		requestCall.enqueue(object : Callback<List<Destination>> {

			// This runs if there is a network error or the API call fails
			override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
				// Here you could show a Toast message or log the error
				// e.g., Toast.makeText(this@DestinationListActivity, "Failed: ${t.message}", Toast.LENGTH_SHORT).show()
			}

			// This runs if the API responds to the request
			override fun onResponse(call: Call<List<Destination>>, response: Response<List<Destination>>) {
				if (response.isSuccessful) {
					// If API call is successful, get the list of destinations from the response
					val destinationList = response.body()!!
					// Set up the RecyclerView with the list using DestinationAdapter
					binding.destinyRecyclerView.adapter = DestinationAdapter(destinationList)
				} else {
					// If something went wrong (e.g., server error), show a message
					Toast.makeText(this@DestinationListActivity, "Response failed: ${response.code()}", Toast.LENGTH_SHORT).show()
				}
			}
		})
	}


}
