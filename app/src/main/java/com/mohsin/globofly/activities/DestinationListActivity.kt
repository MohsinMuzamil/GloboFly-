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

class DestinationListActivity : AppCompatActivity() {

	private lateinit var binding: ActivityDestinyListBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityDestinyListBinding.inflate(layoutInflater)
		setContentView(binding.root)

		setSupportActionBar(binding.toolbar)
		binding.toolbar.title = title

		binding.fab.setOnClickListener {
			val intent = Intent(this@DestinationListActivity, DestinationCreateActivity::class.java)
			startActivity(intent)
		}
	}

	override fun onResume() {
		super.onResume()
		loadDestinations()
	}

	private fun loadDestinations() {

		val destinationService: DestinationService = ServiceBuilder.buildService(DestinationService::class.java)

		val requestCall: Call<List<Destination>> = destinationService.getDestinationList()

		requestCall.enqueue(object : Callback<List<Destination>> {

			override fun onFailure(call: Call<List<Destination>>, t: Throwable) {

			}

			override fun onResponse(call: Call<List<Destination>>, response: Response<List<Destination>>) {
				if (response.isSuccessful) {
					val destinationList = response.body()!!
					binding.destinyRecyclerView.adapter = DestinationAdapter(destinationList)
				} else {
					Toast.makeText(this@DestinationListActivity, "Response failed: ${response.code()}", Toast.LENGTH_SHORT).show()
				}
			}
		})
	}


}
