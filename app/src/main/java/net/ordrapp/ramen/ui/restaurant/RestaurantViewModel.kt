package net.ordrapp.ramen.ui.restaurant

import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import net.ordrapp.ramen.repository.MenuRepository
import javax.inject.Inject

class RestaurantViewModel @Inject constructor(private val repository: MenuRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _menuData = MutableLiveData<List<MenuItem>>()
    val menuData: LiveData<List<MenuItem>> = _menuData

    fun getMenu(uuid: String) {
        repository.getMenuForRestaurant(uuid)
                .subscribe({
                    _menuData::postValue
                }, {
                    it.printStackTrace()
                })
                .addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()

        disposables.clear()
    }

}