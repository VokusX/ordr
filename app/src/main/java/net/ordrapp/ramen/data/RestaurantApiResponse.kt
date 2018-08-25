package net.ordrapp.ramen.data

import com.google.gson.annotations.SerializedName

data class RestaurantApiResponse(@SerializedName("data") val data: List<Restaurant>)