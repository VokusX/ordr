package net.ordrapp.ramen.data

import android.location.Location
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

/**
 * A Location class for use in LatLng
 */
data class Location(@SerializedName("lat") val lat: Double,
                    @SerializedName("lon") val lon: Double) {
    constructor(location: Location) : this(location.latitude, location.longitude)
    constructor(latLng: LatLng) : this(latLng.latitude, latLng.longitude)
}