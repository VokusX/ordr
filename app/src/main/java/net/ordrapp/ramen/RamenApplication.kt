package net.ordrapp.ramen

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import net.ordrapp.ramen.di.ApplicationComponent
import net.ordrapp.ramen.di.ApplicationModule
import net.ordrapp.ramen.di.DaggerApplicationComponent
import net.ordrapp.ramen.di.NetworkModule
import javax.inject.Inject

class RamenApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

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

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingFragmentInjector
}