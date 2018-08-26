package net.ordrapp.ramen.data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MenuItem(@SerializedName("uuid") @PrimaryKey @ColumnInfo(name = "uuid") val uuid: String,
                    @SerializedName("name") @ColumnInfo(name = "name") val name: String,
                    @SerializedName("price") @ColumnInfo(name = "price") val price: Double,
                    @SerializedName("calories") @ColumnInfo(name = "calories") val calories: Long,
                    @SerializedName("pictures") @ColumnInfo(name = "pictures") val pictures: String,
                    @SerializedName("vegetarian") @ColumnInfo(name = "vegetarian") val vegetarian: Boolean = false,
                    @SerializedName("vegan") @ColumnInfo(name = "vegan") val vegan: Boolean = false,
                    @SerializedName("spicy") @ColumnInfo(name = "spicy") val spicy: Boolean = false,
                    @SerializedName("gluten_free") @ColumnInfo(name = "glutenFree") val glutenFree: Boolean = false) {
}

val HAMBURGER = MenuItem("hamburger", "Hamburger", 3.99, 550, "https://images.unsplash.com/pictures-1520072959219-c595dc870360?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=80a0e355a5f83d806f9b72782c7fa19b&auto=format&fit=crop&w=2053&q=80")
val CHEESEBURGER = MenuItem("cheeseburger", "Cheeseburger", 4.99, 600, "https://images.unsplash.com/pictures-1525164286253-al 04e68b9d94c6?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=957038b3b13d6e9a0dece74e4ffa33b8&auto=format&fit=crop&w=668&q=80")

val SCRAMBLED_EGGS = MenuItem("eggs", "Scrambled Eggs", 2.99, 300, "https://images.unsplash.com/pictures-1524250981170-bd522ef63fbf?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=9f18b175dbfd17d1511d924ad03aab2f&auto=format&fit=crop&w=668&q=80")
val PANCAKES = MenuItem("pancakes", "Pancakes", 3.50, 400, "https://images.unsplash.com/pictures-1528301392571-0dfab3c00216?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=5d9cf7b5db701b7e7a025f413d16d162&auto=format&fit=crop&w=1000&q=80")
val FRENCH_TOAST = MenuItem("french_toast", "French Toast", 5.99, 700, ("https://images.unsplash.com/pictures-1528945522327-7032b0774637?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=f404c3049358c90de23a4c76595ac02a&auto=format&fit=crop&w=668&q=80"))