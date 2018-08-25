package net.ordrapp.ramen.data

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user: User)

    @Delete
    fun deleteUser(vararg user: User)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flowable<List<User>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: String): Flowable<User>

}