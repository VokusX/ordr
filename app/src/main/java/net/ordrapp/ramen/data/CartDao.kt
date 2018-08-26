package net.ordrapp.ramen.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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
}