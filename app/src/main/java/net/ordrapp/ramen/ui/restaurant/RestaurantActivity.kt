package net.ordrapp.ramen.ui.restaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_restaurant.*
import net.ordrapp.ramen.R
import net.ordrapp.ramen.data.Restaurant

class RestaurantActivity : AppCompatActivity() {

    private lateinit var restaurant: Restaurant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        restaurant = intent.getParcelableExtra(RESTAURANT_OBJECT_KEY)

        Glide.with(this).load(restaurant.pictures[0]).into(imageView)

        titleText.text = restaurant.name
        ratingBar.rating = restaurant.rating
        reviewCount.text = restaurant.numReviews.toString()
        addressText.text = restaurant.address
        description.text = restaurant.description
    }

    companion object {
        const val RESTAURANT_OBJECT_KEY = "restaurant"
    }
}
