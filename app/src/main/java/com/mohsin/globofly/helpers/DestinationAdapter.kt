// It is responsible for creating and binding each item (row) in the list.

package com.mohsin.globofly.helpers

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mohsin.globoFly.R
import com.mohsin.globofly.activities.DestinationDetailActivity
import com.mohsin.globofly.models.Destination

// This adapter connects the list of Destination objects to the RecyclerView in your UI.
class DestinationAdapter(private val destinationList: List<Destination>) : RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {

	// Called when a new ViewHolder is needed. Inflates the list_item layout for each row.
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

		val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
		return ViewHolder(view)
	}

	// Called for each item to bind its data to the UI.
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		// Get the current destination and set its city name in the TextView
		holder.destination = destinationList[position]
		holder.txvDestination.text = destinationList[position].city
		// When the user clicks on a destination row, open its detail screen
		holder.itemView.setOnClickListener { v ->
			val context = v.context
			val intent = Intent(context, DestinationDetailActivity::class.java)
			// Pass the destination's ID to the detail screen
			intent.putExtra(DestinationDetailActivity.ARG_ITEM_ID, holder.destination!!.id)
			context.startActivity(intent)
		}
	}

	// Returns the total number of items in the list
	override fun getItemCount(): Int {
		return destinationList.size
	}

	// ViewHolder holds references to the views for each list item
	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val txvDestination: TextView = itemView.findViewById(R.id.txv_destination)
		var destination: Destination? = null
		// Optional: Provides a readable string representation of the ViewHolder
		override fun toString(): String {
			return """${super.toString()} '${txvDestination.text}'"""
		}
	}
}
