package net.ordrapp.ramen.data

import com.google.gson.annotations.SerializedName

/**
 * A Restaurant.
 * @param name The name of the restaurant.
 * @param uuid A unique ID for this restaurant.
 * @param rating The restaurant's rating (out of 5).
 * @param numReviews The number of reviews this restaurant has received.
 * @param description A description of the restaurant.
 * @param address The address of the restaurant.
 * @param location The location of the restaurant (LatLng).
 * @param hours The hours of the restaurant (throughout the week).
 * @param checkInAllowed Allows check ins at this restaurant.
 * @param pictures A list of URL's pointing to pictures of this restaurant.
 */
data class Restaurant(@SerializedName("name") val name: String,
                      @SerializedName("uuid") val uuid: String,
                      @SerializedName("rating") val rating: Float,
                      @SerializedName("num_reviews") val numReviews: Long,
                      @SerializedName("description") val description: String,
                      @SerializedName("address") val address: String,
                      @SerializedName("location") val location: Location,
                      @SerializedName("hours") val hours: List<Hour>,
                      @SerializedName("checkInAllowed") val checkInAllowed: Boolean,
                      @SerializedName("pictures") val pictures: List<String>)