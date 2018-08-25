package net.ordrapp.ramen.di

import android.content.Context
import dagger.Module
import dagger.Provides
import net.ordrapp.ramen.RamenApplication
import net.ordrapp.ramen.data.AppDatabase
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: RamenApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase =
            AppDatabase.getInstance(context)
}