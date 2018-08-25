package net.ordrapp.ramen.data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MenuItem(@SerializedName("uuid") @PrimaryKey @ColumnInfo(name = "uuid") val uuid: String,
                    @SerializedName("name") @ColumnInfo(name = "name") val name: String,
                    @SerializedName("price") @ColumnInfo(name = "price") val price: Double,
                    @SerializedName("calories") @ColumnInfo(name = "calories") val calories: Long,
                    @SerializedName("photo") @ColumnInfo(name = "photo") val photo: String,
                    @SerializedName("vegetarian") @ColumnInfo(name = "vegetarian") val vegetarian: Boolean,
                    @SerializedName("vegan") @ColumnInfo(name = "vegan") val vegan: Boolean,
                    @SerializedName("spicy") @ColumnInfo(name = "spicy") val spicy: Boolean,
                    @SerializedName("gluten_free") @ColumnInfo(name = "glutenFree") val glutenFree: Boolean) {
}