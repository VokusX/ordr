package net.ordrapp.ramen.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
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
    }

}