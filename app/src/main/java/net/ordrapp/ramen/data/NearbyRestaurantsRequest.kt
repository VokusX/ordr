package net.ordrapp.ramen.data

import com.google.gson.annotations.SerializedName

data class NearbyRestaurantsRequest(@SerializedName("usr_loc") val userLocation: Location,
                                    @SerializedName("boundaries") val boundaries: List<Location>,
                                    @SerializedName("offset") val offset: Int)