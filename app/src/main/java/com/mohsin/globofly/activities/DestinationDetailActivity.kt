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


class  DestinationDetailActivity : AppCompatActivity() {

	private lateinit var binding: ActivityDestinyDetailBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityDestinyDetailBinding.inflate(layoutInflater)
		setContentView(binding.root)

		setSupportActionBar(binding.detailToolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		val bundle: Bundle? = intent.extras
		if (bundle?.containsKey(ARG_ITEM_ID) == true) {
			val id = intent.getIntExtra(ARG_ITEM_ID, 0)
			loadDetails(id)
			initUpdateButton(id)
			initDeleteButton(id)
		}
	}

	private fun loadDetails(id: Int) {
		val destination = SampleData.getDestinationById(id)
		destination?.let {
			binding.etCity.setText(it.city)
			binding.etDescription.setText(it.description)
			binding.etCountry.setText(it.country)
			binding.collapsingToolbar.title = it.city
		}
	}

	private fun initUpdateButton(id: Int) {
		binding.btnUpdate.setOnClickListener {
			val city = binding.etCity.text.toString()
			val description = binding.etDescription.text.toString()
			val country = binding.etCountry.text.toString()

			val destination = Destination().apply {
				this.id = id
				this.city = city
				this.description = description
				this.country = country
			}

			val service = ServiceBuilder.buildService(DestinationService::class.java)
			val call = service.updateDestination(id, destination)

			call.enqueue(object : Callback<Destination> {
				override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
					finish()
				}

				override fun onFailure(call: Call<Destination>, t: Throwable) {
					Toast.makeText(this@DestinationDetailActivity, "Update failed", Toast.LENGTH_SHORT).show()
				}
			})

		}
	}

	private fun initDeleteButton(id: Int) {
		binding.btnDelete.setOnClickListener {
			val service = ServiceBuilder.buildService(DestinationService::class.java)
			val call = service.deleteDestination(id)

			call.enqueue(object : Callback<Void> {
				override fun onResponse(call: Call<Void>, response: Response<Void>) {
					Toast.makeText(this@DestinationDetailActivity, "Deleted successfully", Toast.LENGTH_SHORT).show()
					finish()
				}

				override fun onFailure(call: Call<Void>, t: Throwable) {
					Toast.makeText(this@DestinationDetailActivity, "Delete failed", Toast.LENGTH_SHORT).show()
				}
			})
		}
	}


	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == android.R.id.home) {
			navigateUpTo(Intent(this, DestinationListActivity::class.java))
			return true
		}
		return super.onOptionsItemSelected(item)
	}

	companion object {
		const val ARG_ITEM_ID = "item_id"
	}
}
