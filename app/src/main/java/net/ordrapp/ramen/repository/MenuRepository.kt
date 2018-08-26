package net.ordrapp.ramen.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.ordrapp.ramen.data.ApiService
import net.ordrapp.ramen.data.AppDatabase
import net.ordrapp.ramen.data.MenuItem
import net.ordrapp.ramen.data.MenuRequest
import javax.inject.Inject

class MenuRepository @Inject constructor(private val database: AppDatabase,
                                         private val apiService: ApiService) {

    fun getMenuForRestaurant(uuid: String): Single<Map<String, List<MenuItem>>> =
            apiService.getMenu(MenuRequest(uuid))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

}