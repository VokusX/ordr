package net.ordrapp.ramen.data

import com.google.gson.annotations.SerializedName

data class Restaurant(@SerializedName("name") val name: String,
                      @SerializedName("rating") val rating: Float)