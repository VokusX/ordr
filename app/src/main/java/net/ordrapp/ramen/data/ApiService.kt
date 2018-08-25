package net.ordrapp.ramen.data

import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("5b810a6b3400008400ecb2e0")
    fun getNearbyRestaurants(): Single<RestaurantApiResponse>

}