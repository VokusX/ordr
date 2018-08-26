package net.ordrapp.ramen.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.ordrapp.ramen.MainActivity
import net.ordrapp.ramen.ui.OnboardingActivity
import net.ordrapp.ramen.ui.cart.CartActivity
import net.ordrapp.ramen.ui.menu.MenuActivity
import net.ordrapp.ramen.ui.restaurant.RestaurantActivity

@Module
abstract class BuildersModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindOnboardingActivity(): OnboardingActivity

    @ContributesAndroidInjector
    abstract fun bindCartActivity(): CartActivity

    @ContributesAndroidInjector
    abstract fun bindRestaurantActivity(): RestaurantActivity

    @ContributesAndroidInjector
    abstract fun bindMenuActivity(): MenuActivity

}