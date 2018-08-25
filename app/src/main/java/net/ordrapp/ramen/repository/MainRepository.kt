package net.ordrapp.ramen.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.ordrapp.ramen.data.ApiService
import net.ordrapp.ramen.data.NearbyRestaurantsRequest
import net.ordrapp.ramen.data.RestaurantApiResponse
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    fun getNearbyRestaurants(request: NearbyRestaurantsRequest): Single<RestaurantApiResponse> {
        return apiService.getNearbyRestaurants(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}