package net.ordrapp.ramen.ui.restaurant

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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

        addressLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val geoUri = Uri.parse("geo:0,0?q=${restaurant.address}")

            intent.data = geoUri
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finishAfterTransition()
            null -> { }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val RESTAURANT_OBJECT_KEY = "restaurant"
    }
}
