package net.ordrapp.ramen.repository

import net.ordrapp.ramen.data.ApiService
import net.ordrapp.ramen.data.AppDatabase
import javax.inject.Inject

class CartRepository @Inject constructor(private val database: AppDatabase,
                                         private val apiService: ApiService) {
}