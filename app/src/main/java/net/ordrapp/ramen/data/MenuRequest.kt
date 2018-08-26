package net.ordrapp.ramen.data

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * A request to get the menu of a restaurant.
 * @param uuid The UUID of the restaurant to get the menu of
 * @param time The time of the day the request is being made (e.g. 0800, 1500).
 */
data class MenuRequest(@SerializedName("uuid") val uuid: String,
                       @SerializedName("time") var time: Int) {

    constructor(uuid: String, date: Date = Date()) : this(uuid, 0) {
        val calendar = Calendar.getInstance()
        calendar.time = date

        time = calendar.get(Calendar.HOUR_OF_DAY) * 100 + calendar.get(Calendar.MINUTE)
    }

}