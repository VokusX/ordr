package net.ordrapp.ramen.data

import com.google.gson.annotations.SerializedName

data class RestaurantApiResponse(@SerializedName("restaurants") val restaurants: List<Restaurant>)