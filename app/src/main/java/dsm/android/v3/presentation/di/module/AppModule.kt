package dsm.android.v3.presentation.di.module

import android.app.Activity
import android.app.Application
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dsm.android.v3.data.local.shared.SharedPrefStorage
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [(ApiModule::class),(NetworkModule::class),(LocalModule::class)])
class AppModule(val application: Application) {
    @Provides
    @Singleton
    fun provideApp() = application
}