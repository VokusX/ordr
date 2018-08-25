package net.ordrapp.ramen.ui.home

import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.VisibleRegion
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import net.ordrapp.ramen.data.NearbyRestaurantsRequest
import net.ordrapp.ramen.data.Restaurant
import net.ordrapp.ramen.repository.MainRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _restaurantsData = MutableLiveData<RestaurantResults>()
    val restaurantsData: LiveData<RestaurantResults> = _restaurantsData

    private val _userLocation = MutableLiveData<Location>()
    val userLocation: LiveData<Location> = _userLocation
    fun updateUserLocation(newLocation: Location) {
        _userLocation.value = newLocation
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

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

    fun getNearbyStops(visibleRegion: VisibleRegion) {
        _isLoading.value = true

        val request = if (_userLocation.value == null) {
            NearbyRestaurantsRequest(net.ordrapp.ramen.data.Location(visibleRegion.latLngBounds.center),
                    listOf(net.ordrapp.ramen.data.Location(visibleRegion.latLngBounds.northeast), net.ordrapp.ramen.data.Location(visibleRegion.latLngBounds.southwest)), 25)
        } else {
            NearbyRestaurantsRequest(net.ordrapp.ramen.data.Location(_userLocation.value!!),
                    listOf(net.ordrapp.ramen.data.Location(visibleRegion.latLngBounds.northeast), net.ordrapp.ramen.data.Location(visibleRegion.latLngBounds.southwest)), 25)
        }
        repository.getNearbyRestaurants(request)
                .subscribe(
                        {
                            Log.d("MainActivity", it.restaurants.size.toString())
                            restaurantsPublisher.onNext(it.restaurants)
                            _isLoading.postValue(false)
                        },
                        {
                            it.printStackTrace()
                            _isLoading.postValue(false)
                        })
                .addTo(disposables)
    }

}