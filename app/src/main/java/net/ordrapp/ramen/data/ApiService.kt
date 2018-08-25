package net.ordrapp.ramen.data

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("search/nearby")
    fun getNearbyRestaurants(@Body request: NearbyRestaurantsRequest): Single<RestaurantApiResponse>

    @POST("search/menu")
    fun getMenu(@Body request: MenuRequest): Single<List<MenuItem>>

    @POST("order")
    fun placeOrder()

}