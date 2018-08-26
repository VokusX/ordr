package net.ordrapp.ramen.repository

import net.ordrapp.ramen.data.ApiService
import net.ordrapp.ramen.data.AppDatabase
import net.ordrapp.ramen.data.CartItem
import net.ordrapp.ramen.utils.runOnIoThread
import javax.inject.Inject

class CartRepository @Inject constructor(private val database: AppDatabase,
                                         private val apiService: ApiService) {
    fun removeFromCart(item: CartItem){
        runOnIoThread {
            database.cartDao().deleteItem(item)
        }
    }
}