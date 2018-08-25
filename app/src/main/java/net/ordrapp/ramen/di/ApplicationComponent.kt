package net.ordrapp.ramen.di

import dagger.Component
import dagger.android.AndroidInjector
import net.ordrapp.ramen.RamenApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, BuildersModule::class, ViewModelModule::class])
interface ApplicationComponent : AndroidInjector<RamenApplication>