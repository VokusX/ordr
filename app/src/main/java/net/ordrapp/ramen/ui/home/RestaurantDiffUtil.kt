package net.ordrapp.ramen.ui.home

import androidx.recyclerview.widget.DiffUtil
import net.ordrapp.ramen.data.Restaurant

class RestaurantDiffUtil(private val oldItems: List<Restaurant>, private val newItems: List<Restaurant>)
    : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].uuid == newItems[newItemPosition].uuid

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}