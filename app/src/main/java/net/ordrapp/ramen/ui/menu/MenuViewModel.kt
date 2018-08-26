package net.ordrapp.ramen.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import net.ordrapp.ramen.data.MenuItem
import net.ordrapp.ramen.repository.MenuRepository
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val repository: MenuRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _menuData = MutableLiveData<Map<String, List<MenuItem>>>()
    val menuData: LiveData<Map<String, List<MenuItem>>> = _menuData

    fun getMenu(uuid: String) {
        repository.getMenuForRestaurant(uuid)
                .subscribe({
                    _menuData.value = it
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