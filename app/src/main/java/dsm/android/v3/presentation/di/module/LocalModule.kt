package dsm.android.v3.presentation.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import dsm.android.v3.data.local.shared.SharedPrefStorage
import dsm.android.v3.data.local.database.AuthDatabase
import javax.inject.Singleton
import android.arch.persistence.room.Room
import dsm.android.v3.data.local.dao.AuthDao


@Module
class LocalModule(val application: Application) {

    private val authDatabase: AuthDatabase

    init {
        authDatabase = Room.databaseBuilder(application, AuthDatabase::class.java, "auth.db").build()
    }
    @Provides
    @Singleton
    fun provideLocalStorage() = SharedPrefStorage(application)

    @Singleton
    @Provides
    fun provideRoomDatabase(): AuthDatabase = authDatabase

    @Singleton
    @Provides
    fun provideAuthDao(authDatabase: AuthDatabase): AuthDao = authDatabase.getAuthDao()
}