package net.ordrapp.ramen.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.ordrapp.ramen.MainActivity
import net.ordrapp.ramen.ui.OnboardingActivity

@Module
abstract class BuildersModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindOnboardingActivity(): OnboardingActivity

}