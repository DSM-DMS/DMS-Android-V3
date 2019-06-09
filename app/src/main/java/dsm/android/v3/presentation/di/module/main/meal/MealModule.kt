package dsm.android.v3.presentation.di.module.main.meal

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import dsm.android.v3.data.local.dao.MealDao
import dsm.android.v3.data.local.database.MealDatabase
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.meal.MealRepository
import dsm.android.v3.domain.repository.meal.MealRepositoryImpl
import dsm.android.v3.presentation.di.scope.FragmentScope

@Module
class MealModule {
    @FragmentScope
    @Provides
    fun provideDataBase(context: Context) : MealDatabase
            = Room.databaseBuilder(context, MealDatabase::class.java, "meal.db").build()

    @FragmentScope
    @Provides
    fun provideDao(database: MealDatabase): MealDao
            = database.getMealDao()

    @FragmentScope
    @Provides
    fun provideRepository(apiClient: ApiClient, dao: MealDao): MealRepository
            = MealRepositoryImpl(apiClient, dao)
}