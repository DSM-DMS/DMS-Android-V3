package dsm.android.v3.presentation.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import dsm.android.v3.data.local.shared.SharedPrefStorage
import dsm.android.v3.data.local.database.AuthDatabase
import javax.inject.Singleton
import android.arch.persistence.room.Room
import android.content.Context
import dsm.android.v3.data.local.dao.AuthDao
import dsm.android.v3.data.local.shared.LocalStorage


@Module
class LocalModule {

    @Provides
    @Singleton
    fun provideAuthDataBase(context: Context) : AuthDatabase
            = Room.databaseBuilder(context, AuthDatabase::class.java, "auth.db").build()


    @Provides
    @Singleton
    fun provideLocalStorage(context: Context) : LocalStorage = SharedPrefStorage(context)


    @Singleton
    @Provides
    fun provideAuthDao(authDatabase: AuthDatabase): AuthDao = authDatabase.getAuthDao()
}