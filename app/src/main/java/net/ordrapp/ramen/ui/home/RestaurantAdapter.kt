package net.ordrapp.ramen.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.restaurant_row.view.*
import net.ordrapp.ramen.R
import net.ordrapp.ramen.data.Restaurant
import net.ordrapp.ramen.ui.restaurant.RestaurantActivity
import androidx.core.content.ContextCompat.startActivity
import android.os.Bundle
import android.app.Activity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair


class RestaurantAdapter(private val context: Context, var userLastLocation: android.location.Location?, var values: List<Restaurant> = emptyList())
    : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.restaurant_row, parent, false))
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val intent = Intent(context, RestaurantActivity::class.java)
                intent.putExtra(RestaurantActivity.RESTAURANT_OBJECT_KEY, values[adapterPosition])

                val extras = ArrayList<androidx.core.util.Pair<View, String>>()
                extras.add(Pair.create(itemView.imageView, "image"))

                val sharedTransition = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        context as Activity,
                        *extras.toTypedArray()
                )

                val bundle = Bundle()
                bundle.putAll(sharedTransition.toBundle())

                context.startActivity(intent, bundle)
            }
        }

        fun bind(item: Restaurant) {
            itemView.title.text = item.name
            itemView.ratingBar.rating = item.rating
            itemView.ratingCount.text = item.numReviews.toString()

            itemView.hereNotice.visibility = View.GONE
            itemView.checkInNowButton.visibility = View.GONE

            if (userLastLocation != null) {
                val dummyLocation = android.location.Location("dummy").apply {
                    latitude = item.location.lat
                    longitude = item.location.lon
                }

                val distance = userLastLocation!!.distanceTo(dummyLocation)

                if (distance < 100f) {
                    itemView.distance.text = context.getString(R.string.distance_metres, distance)
                } else {
                    itemView.distance.text = context.getString(R.string.distance_kilometres, distance / 1000)
                }

                if (adapterPosition == 0 && distance < 15f) {
                    itemView.hereNotice.visibility = View.VISIBLE
                    itemView.checkInNowButton.visibility = View.VISIBLE
                }

            }

            if (item.pictures != null && item.pictures.isNotEmpty()) {
                Glide.with(itemView).load(item.pictures[0]).into(itemView.imageView)
            }

        }
    }
}