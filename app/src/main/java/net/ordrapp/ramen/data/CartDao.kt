package net.ordrapp.ramen.data

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface CartDao {


    @Insert
    fun insertNewItem(vararg item: CartItem)

    @Update
    fun updateItem(item: CartItem)

    @Query("SELECT * FROM cart")
    fun getCart(): Flowable<List<CartItem>>

    @Query("DELETE FROM cart")
    fun clearCart()

    @Delete
    fun deleteItem(item: CartItem)
}