package net.ordrapp.ramen.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Data class representing the hours of a restaurant.
 * @param dayOfWeek The day of week this "hour" represents (monday, tuesday, wednesday, etc.)
 * @param open The time of day the restaurant opens (e.g. 1200, 0800, 1500, etc.)
 * @param close The time of day the restaurant closes (e.g 0200, 2200, etc.)
 * @param timezone The timezone these hours are in. (e.g. EST).
 */
data class Hour(@SerializedName("dayOfWeek") val dayOfWeek: String,
                @SerializedName("open") val open: Int,
                @SerializedName("close") val close: Int,
                @SerializedName("timezone") val timezone: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString()!!)

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(dayOfWeek)
        parcel.writeInt(open)
        parcel.writeInt(close)
        parcel.writeString(timezone)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Hour> {
        override fun createFromParcel(parcel: Parcel): Hour {
            return Hour(parcel)
        }

        override fun newArray(size: Int): Array<Hour?> {
            return arrayOfNulls(size)
        }
    }

}