package net.ordrapp.ramen.data

import android.location.Location
import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

/**
 * A Location class for use in LatLng
 */
data class Location(@SerializedName("lat") val lat: Double,
                    @SerializedName("lon") val lon: Double) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readDouble(),
            parcel.readDouble())

    constructor(location: Location) : this(location.latitude, location.longitude)
    constructor(latLng: LatLng) : this(latLng.latitude, latLng.longitude)

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeDouble(lat)
        parcel.writeDouble(lon)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<net.ordrapp.ramen.data.Location> {
        override fun createFromParcel(parcel: Parcel): net.ordrapp.ramen.data.Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<net.ordrapp.ramen.data.Location?> {
            return arrayOfNulls(size)
        }
    }


}