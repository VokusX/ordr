package net.ordrapp.ramen.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.restaurant_row.view.*
import net.ordrapp.ramen.R
import net.ordrapp.ramen.data.Restaurant

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
            itemView.setOnClickListener { /* TODO */ }
        }

        fun bind(item: Restaurant) {
            itemView.title.text = item.name
            itemView.ratingBar.rating = item.rating
            itemView.ratingCount.text = item.numReviews.toString()

            if (userLastLocation != null) {
                val dummyLocation = android.location.Location("dummy").apply {
                    latitude = item.location.lat
                    longitude = item.location.lon
                }

                val distance = userLastLocation!!.distanceTo(dummyLocation)

                if (distance < 100f) {
                    itemView.distance.text = context.getString(R.string.distance_metres, distance)
                } else {
                    itemView.distance.text = context.getString(R.string.distance_kilometres, distance / 100)
                }

            }

            Glide.with(itemView).load(item.pictures[0]).into(itemView.imageView)
        }
    }
}