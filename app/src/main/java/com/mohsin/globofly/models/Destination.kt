package com.mohsin.globofly.models

// This is a simple data class representing a travel destination.
// It holds the information for a single destination and is used throughout the app
// for displaying, creating, updating, and deleting destinations.

data class Destination(
	var id: Int = 0,
	var city: String? = null,
	var description: String? = null,
	var country: String? = null
)
