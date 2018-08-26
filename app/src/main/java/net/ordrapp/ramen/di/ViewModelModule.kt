package net.ordrapp.ramen.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.ordrapp.ramen.ui.cart.CartViewModel
import net.ordrapp.ramen.ui.home.MainViewModel
import net.ordrapp.ramen.ui.menu.MenuViewModel
import net.ordrapp.ramen.ui.restaurant.RestaurantViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    abstract fun bindCartViewModel(mainViewModel: CartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantViewModel::class)
    abstract fun bindRestaurantViewModel(mainViewModel: RestaurantViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    abstract fun bindMenuViewModel(mainViewModel: MenuViewModel): ViewModel

}