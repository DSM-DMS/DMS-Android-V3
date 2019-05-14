package dsm.android.v3.presentation.di.module.main.meal

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.meal.MealRepository
import dsm.android.v3.domain.repository.meal.MealRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.presentation.viewModel.meal.MealViewModelFactory

@Module
class MealModule {
    @FragmentScope
    @Provides
    fun provideRepository(apiClient: ApiClient): MealRepository
            = MealRepositoryImpl(apiClient)

    @FragmentScope
    @Provides
    fun provideViewModelFactory(repository: MealRepository): MealViewModelFactory
            = MealViewModelFactory(repository)
}