package net.ordrapp.ramen.data

import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("5b80f1333400005400ecb2cd")
    fun getNearbyRestaurants(): Single<RestaurantApiResponse>

}