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

	private lateinit var binding: ActivityDestinyCreateBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityDestinyCreateBinding.inflate(layoutInflater)
		setContentView(binding.root)

		setSupportActionBar(binding.toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		binding.btnAdd.setOnClickListener {
			val newDestination = Destination().apply {
				city = binding.etCity.text.toString()
				description = binding.etDescription.text.toString()
				country = binding.etCountry.text.toString()
			}

			val service = ServiceBuilder.buildService(DestinationService::class.java)
			val call = service.addDestination(newDestination)

			call.enqueue(object : Callback<Destination> {
				override fun onFailure(call: Call<Destination>, t: Throwable) {
					Toast.makeText(this@DestinationCreateActivity, "Failed to create: ${t.message}", Toast.LENGTH_LONG).show()
					t.printStackTrace()
				}

				override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
					if (response.isSuccessful) {
						Toast.makeText(this@DestinationCreateActivity, "Created successfully!", Toast.LENGTH_SHORT).show()
						finish()
					} else {
						Toast.makeText(this@DestinationCreateActivity, "Failed: ${response.code()} - ${response.message()}", Toast.LENGTH_LONG).show()
					}
				}

			})
		 }
	}
}
