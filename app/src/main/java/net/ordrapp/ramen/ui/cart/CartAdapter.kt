package net.ordrapp.ramen.ui.cart

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_restaurant.view.*
import kotlinx.android.synthetic.main.menu_item_row.view.*
import kotlinx.android.synthetic.main.restaurant_row.view.*
import net.ordrapp.ramen.R
import net.ordrapp.ramen.data.CartItem

class CartAdapter(private val context: Context, var cartItems: List<CartItem> = emptyList(), private val view : CartViewModel) :
        RecyclerView.Adapter<CartAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.menu_item_row, parent, false))
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.removeButton.setOnClickListener {
                view.deleteItem(cartItems[adapterPosition])
            }
        }
        fun bind(item: CartItem){
            itemView.title.text = item.item.name
            itemView.itemCalories.text = item.item.calories.toString()
            itemView.price.text = item.item.price.toString()
            itemView.multiplierText.text  = item.quantity.toString()

            if(item.item.photo.isNotEmpty())
                Glide.with(itemView).load(item.item.photo[0]).into(itemView.imageView2)
        }
    }
}