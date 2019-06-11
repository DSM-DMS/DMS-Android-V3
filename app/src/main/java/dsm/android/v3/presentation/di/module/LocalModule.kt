package dsm.android.v3.presentation.di.module

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import dsm.android.v3.data.local.shared.SharedPrefStorage
import javax.inject.Singleton
import android.content.Context
import dsm.android.v3.data.local.dao.OfflineDao
import dsm.android.v3.data.local.database.OfflineDatabase
import dsm.android.v3.data.local.shared.LocalStorage

@Module
class LocalModule {
    @Provides
    @Singleton
    fun provideOfflineDatabase(context: Context): OfflineDatabase
        = Room.databaseBuilder(context, OfflineDatabase::class.java, "offline.db").build()

    @Provides
    @Singleton
    fun provideDao(database: OfflineDatabase): OfflineDao = database.getOfflineDao()

    @Provides
    @Singleton
    fun provideLocalStorage(context: Context) : LocalStorage = SharedPrefStorage(context)
}