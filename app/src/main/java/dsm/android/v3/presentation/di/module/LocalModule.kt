package dsm.android.v3.presentation.di.module

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.local.shared.SharedPrefStorage
import javax.inject.Singleton
import android.content.Context
import dsm.android.v3.data.local.shared.LocalStorage


@Module
class LocalModule {

    @Provides
    @Singleton
    fun provideLocalStorage(context: Context) : LocalStorage = SharedPrefStorage(context)
}