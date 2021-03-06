package net.ordrapp.ramen.ui.cart

import androidx.lifecycle.ViewModel
import net.ordrapp.ramen.data.CartItem
import net.ordrapp.ramen.repository.CartRepository
import javax.inject.Inject

class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {

    fun deleteItem(item : CartItem){
        repository.removeFromCart(item)
    }

}