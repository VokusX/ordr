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
                      @SerializedName("pictures") val pictures: List<String>) {
    companion object {

        val RESTAURANT_A = Restaurant("Restaurant A",
                "UUIDForA",
                4.5f,
                501,
                "Restaurant A serves amazing potatoes and tomato dishes.",
                "1125 Colonel By Dr.",
                Location(43.667631, -79.391873),
                listOf(Hour("monday", 800, 1500, "EST")),
                true,
                listOf("https://images.unsplash.com/photo-1521017432531-fbd92d768814?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=e813bc9e93c2b7ede92476a3eddf3606&auto=format&fit=crop&w=1500&q=80"))

        val RESTAURANT_B = Restaurant("Restaurant B",
                "UUIDForB",
                4.2f,
                257,
                "Restaurant B serves amazing cheese and beef dishes.",
                "1112 Colonel By Dr.",
                Location(43.667185, -79.394631),
                listOf(Hour("tuesday", 800, 1500, "EST")),
                true,
                listOf("https://images.unsplash.com/photo-1449198063792-7d754d6f3c80?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjF9&s=34dc780dd28d924901482a86ebdda676&auto=format&fit=crop&w=1051&q=80"))

        val RESTAURANT_C = Restaurant("Restaurant C",
                "UUIDForC",
                3.2f,
                437,
                "Restaurant C serves mediocre computer science homework assignments.",
                "1113 Colonel By Dr.",
                Location(43.669205, -79.393574),
                listOf(Hour("thursday", 900, 2200, "EST")),
                true,
                listOf("https://images.unsplash.com/photo-1516903022779-81a73028310f?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=b5170ed4c4857dd6f0494cba2b47b7ee&auto=format&fit=crop&w=1185&q=80"))

        val RESTAURANTS = listOf(RESTAURANT_A, RESTAURANT_B, RESTAURANT_C)
    }
}