package net.ordrapp.ramen.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.ordrapp.ramen.data.ApiService
import net.ordrapp.ramen.data.RestaurantApiResponse
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    fun getNearbyRestaurants(): Single<RestaurantApiResponse> {
        return apiService.getNearbyRestaurants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}