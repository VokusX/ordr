package net.ordrapp.ramen.data

import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("nearby")
    fun getNearbyRestaurants(): Single<List<Restaurant>>

}