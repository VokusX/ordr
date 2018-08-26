package net.ordrapp.ramen.repository

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.ordrapp.ramen.data.*
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService,
                                         private val database: AppDatabase) {

    fun getNearbyRestaurants(request: NearbyRestaurantsRequest): Single<RestaurantApiResponse> {
        return apiService.getNearbyRestaurants(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getUserById(id: String): Flowable<User> {
        return database.userDao().getUserById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}