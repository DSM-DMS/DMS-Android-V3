package dsm.android.v3.presentation.di.module.pointLog

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.meal.MealRepository
import dsm.android.v3.domain.repository.meal.MealRepositoryImpl
import dsm.android.v3.domain.repository.pointLog.PointLogRepository
import dsm.android.v3.domain.repository.pointLog.PointLogRepositoryImpl
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.presentation.viewModel.meal.MealViewModelFactory

@Module
class PointLogModule {
    @FragmentScope
    @Provides
    fun provideRepository(apiClient: ApiClient): PointLogRepository
            = PointLogRepositoryImpl(apiClient)
}