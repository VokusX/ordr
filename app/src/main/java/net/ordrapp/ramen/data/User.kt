package net.ordrapp.ramen.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(@ColumnInfo(name = "name") val name: String,
                @ColumnInfo(name = "givenName") val givenName: String?,
                @ColumnInfo(name = "familyName") val familyName: String,
                @ColumnInfo(name = "email") val email: String,
                @ColumnInfo(name = "id") @PrimaryKey val id: String,
                @ColumnInfo(name = "pictures") val photo: String)