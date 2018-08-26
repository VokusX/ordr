package net.ordrapp.ramen.ui.restaurant

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_restaurant.*
import net.ordrapp.ramen.R
import net.ordrapp.ramen.data.Restaurant
import net.ordrapp.ramen.ui.menu.MenuActivity
import net.ordrapp.ramen.ui.menu.MenuAdapter
import javax.inject.Inject

class RestaurantActivity : AppCompatActivity() {

    private lateinit var restaurant: Restaurant

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: RestaurantViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[RestaurantViewModel::class.java]

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

        viewModel.getMenu(restaurant.uuid)
        viewModel.menuData.observe(this, Observer {
            val sublist = it.subList(0, if (it.size <= 3) it.size else 3)
            recyclerView.adapter = MenuAdapter(this@RestaurantActivity, sublist)
        })

        menuLink.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("uuid", restaurant.uuid)

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
