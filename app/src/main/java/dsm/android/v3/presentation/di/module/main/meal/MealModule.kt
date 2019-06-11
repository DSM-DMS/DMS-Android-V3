package dsm.android.v3.presentation.di.module.main.meal

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.local.dao.OfflineDao
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.meal.MealRepository
import dsm.android.v3.domain.repository.meal.MealRepositoryImpl
import dsm.android.v3.presentation.di.scope.FragmentScope

@Module
class MealModule{
    @FragmentScope
    @Provides
    fun provideRepository(apiClient: ApiClient, dao: OfflineDao): MealRepository
            = MealRepositoryImpl(apiClient, dao)
}