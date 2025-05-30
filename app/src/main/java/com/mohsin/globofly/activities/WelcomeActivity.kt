// This Activity displays a welcome screen to the user when the app starts.

package com.mohsin.globofly.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mohsin.globoFly.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

	// View binding variable for accessing the layout's views easily
	private lateinit var binding: ActivityWelcomeBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		// Inflate the layout using view binding
		binding = ActivityWelcomeBinding.inflate(layoutInflater)
		setContentView(binding.root)

		// This can be replaced in the future with a message fetched from the backend.
		binding.message.text = "Black Friday! Get 25% cash back on saving your first spot."
	}

	// This function is called when the user taps the "Get Started" button.
	// It opens the DestinationListActivity and closes (finishes) the welcome screen.
	fun getStarted(view: View) {
		val intent = Intent(this, DestinationListActivity::class.java)
		startActivity(intent)
		finish()
	}
}
