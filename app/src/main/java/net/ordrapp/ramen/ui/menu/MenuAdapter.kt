package net.ordrapp.ramen.ui.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.menu_item_row.view.*
import kotlinx.android.synthetic.main.restaurant_row.view.*
import net.ordrapp.ramen.R
import net.ordrapp.ramen.data.MenuItem
import net.ordrapp.ramen.ui.restaurant.RestaurantViewModel

class MenuAdapter(private val context: Context, var values: List<MenuItem>, val menuViewModel: MenuViewModel? = null)
    : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.menu_item_row, parent, false))

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MenuItem) {
            itemView.title.text = item.name
            itemView.itemCalories.text = item.calories.toString()
            itemView.price.text = item.price.toString()
            itemView.multiplierText.visibility = View.GONE

            if (menuViewModel == null) {
                itemView.relativeLayout.visibility = View.GONE
            }

            if(item.photo.isNotEmpty()) {
                Glide.with(itemView).load(item.photo[0]).into(itemView.imageView2)
            }
        }
    }

}