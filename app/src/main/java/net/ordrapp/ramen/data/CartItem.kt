package net.ordrapp.ramen.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
class CartItem {
    @ColumnInfo(name = "quantity")
    var quantity: Int = 0

    @Embedded
    lateinit var item: MenuItem
}