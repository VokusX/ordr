package net.ordrapp.ramen.ui.home

import androidx.recyclerview.widget.DiffUtil
import net.ordrapp.ramen.data.Restaurant

/**
 * The new results of a list of restaurants.
 * @param restaurants The list of restaurants that will now be displayed
 * @param diffResult The diff result between the current list stored in [restaurants] and the previous list.
 */
data class RestaurantResults(val restaurants: List<Restaurant> = emptyList(), val diffResult: DiffUtil.DiffResult = EMPTY_RESULT)

private val EMPTY_RESULT = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
    override fun getOldListSize() = 0
    override fun getNewListSize() = 0
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = throw AssertionError()
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = throw AssertionError()
})