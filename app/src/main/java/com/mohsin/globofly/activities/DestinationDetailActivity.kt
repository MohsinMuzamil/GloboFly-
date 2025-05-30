// It allows the user to view, update, or delete the destination.

package com.mohsin.globofly.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mohsin.globoFly.databinding.ActivityDestinyDetailBinding
import com.mohsin.globofly.helpers.SampleData
import com.mohsin.globofly.models.Destination
import com.mohsin.globofly.services.ServiceBuilder
import com.mohsin.globofly.services.DestinationService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast

// This Activity displays the details of a Destination.
class  DestinationDetailActivity : AppCompatActivity() {

	// View Binding to access views in the activity_detail layout
	private lateinit var binding: ActivityDestinyDetailBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		// Inflate the layout and initialize binding
		binding = ActivityDestinyDetailBinding.inflate(layoutInflater)
		setContentView(binding.root)

		// Set up the toolbar at the top of the screen
		setSupportActionBar(binding.detailToolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		// Get the extras (data) passed to this activity (from previous screen)
		val bundle: Bundle? = intent.extras
		// Check if the intent contains an "item_id" for the destination
		if (bundle?.containsKey(ARG_ITEM_ID) == true) {
			val id = intent.getIntExtra(ARG_ITEM_ID, 0)
			// Load the details for this destination and show them on the screen
			loadDetails(id)
			// Set up the update button to allow editing the destination
			initUpdateButton(id)
			// Set up the delete button to allow deleting the destination
			initDeleteButton(id)
		}
	}

	// Loads the destination details either from sample data (offline) or from API (online)
	private fun loadDetails(id: Int) {
		// This uses local sample data.
		// In a real app, you would fetch the destination from the API instead.
		val destination = SampleData.getDestinationById(id)
		destination?.let {
			binding.etCity.setText(it.city)
			binding.etDescription.setText(it.description)
			binding.etCountry.setText(it.country)
			// Set the title of the toolbar to the city name
			binding.collapsingToolbar.title = it.city
		}
	}

	// Sets up the update button: when clicked, it sends the updated data to the backend API
	private fun initUpdateButton(id: Int) {
		binding.btnUpdate.setOnClickListener {
			// Get the updated values from the input fields
			val city = binding.etCity.text.toString()
			val description = binding.etDescription.text.toString()
			val country = binding.etCountry.text.toString()

			// Create a new Destination object with the updated data
			val destination = Destination().apply {
				this.id = id
				this.city = city
				this.description = description
				this.country = country
			}

			// Build the Retrofit service to communicate with the API
			val service = ServiceBuilder.buildService(DestinationService::class.java)
			// Create the API call to update the destination
			val call = service.updateDestination(id, destination)

			// Execute the API call asynchronously
			call.enqueue(object : Callback<Destination> {
				// On successful response, close this screen (go back)
				override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
					finish()
				}

				// On error, show a toast message
				override fun onFailure(call: Call<Destination>, t: Throwable) {
					Toast.makeText(this@DestinationDetailActivity, "Update failed", Toast.LENGTH_SHORT).show()
				}
			})

		}
	}

	// Sets up the delete button: when clicked, it sends a delete request to the backend API
	private fun initDeleteButton(id: Int) {
		binding.btnDelete.setOnClickListener {
			// Build the Retrofit service to communicate with the API
			val service = ServiceBuilder.buildService(DestinationService::class.java)
			// Create the API call to delete the destination
			val call = service.deleteDestination(id)

			// Execute the API call asynchronously
			call.enqueue(object : Callback<Void> {
				// On successful response, show a toast and close this screen
				override fun onResponse(call: Call<Void>, response: Response<Void>) {
					Toast.makeText(this@DestinationDetailActivity, "Deleted successfully", Toast.LENGTH_SHORT).show()
					finish()
				}
				// On error, show a toast message
				override fun onFailure(call: Call<Void>, t: Throwable) {
					Toast.makeText(this@DestinationDetailActivity, "Delete failed", Toast.LENGTH_SHORT).show()
				}
			})
		}
	}

	// Handles the action when the user presses the back/up button in the toolbar
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == android.R.id.home) {
			// Go back to the list activity
			navigateUpTo(Intent(this, DestinationListActivity::class.java))
			return true
		}
		return super.onOptionsItemSelected(item)
	}

	companion object {
		// Constant used as a key for passing the destination id between activities
		const val ARG_ITEM_ID = "item_id"
	}
}
