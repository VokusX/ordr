package net.ordrapp.ramen.ui.home

import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import net.ordrapp.ramen.data.Restaurant
import net.ordrapp.ramen.repository.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _restaurantsData = MutableLiveData<RestaurantResults>()
    val restaurantsData: LiveData<RestaurantResults> = _restaurantsData

    private val disposables = CompositeDisposable()

    private val restaurantsPublisher = PublishProcessor.create<List<Restaurant>>()

    init {
        restaurantsPublisher
                .observeOn(Schedulers.computation())
                .scan(RestaurantResults()) { (oldList), newList ->
                    val diffResult = DiffUtil.calculateDiff(RestaurantDiffUtil(oldList, newList))
                    RestaurantResults(newList, diffResult)
                }
                .skip(1)
                .subscribe(_restaurantsData::postValue)
                .addTo(disposables)
    }

    fun getNearbyStops(location: Location?) {
        repository.getNearbyRestaurants()
                .subscribe({
                    Log.d("MainActivity", it.data.size.toString())
                    restaurantsPublisher.onNext(it.data)
                }, {
                    it.printStackTrace()
                })
                .addTo(disposables)
    }

}