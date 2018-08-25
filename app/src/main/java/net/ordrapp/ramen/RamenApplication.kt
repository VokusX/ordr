package net.ordrapp.ramen

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.ordrapp.ramen.di.ApplicationComponent
import net.ordrapp.ramen.di.ApplicationModule
import net.ordrapp.ramen.di.DaggerApplicationComponent
import net.ordrapp.ramen.di.NetworkModule
import javax.inject.Inject

class RamenApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        buildComponent().also { it.inject(this) }
    }

    private fun buildComponent(): ApplicationComponent = DaggerApplicationComponent
            .builder()
            .networkModule(NetworkModule())
            .applicationModule(ApplicationModule(this))
            .build()

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}