package net.ordrapp.ramen.data

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

/**
 * A Location class for use in LatLng
 */
data class Location(@SerializedName("lat") val lat: Double,
                    @SerializedName("lon") val lon: Double)