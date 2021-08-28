package dsm.android.v3.presentation.di.module.applyMeal

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.applyMeal.ApplyMealRepository
import dsm.android.v3.domain.repository.applyMeal.ApplyMealRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.viewModel.applyMeal.ApplyMealViewModelFactory

@Module
class ApplyMealModule {

        @ActivityScope
        @Provides
        fun provideRepository(apiClient: ApiClient): ApplyMealRepository =
            ApplyMealRepositoryImpl(apiClient)

        @ActivityScope
        @Provides
        fun provideViewModelFactory(
            repository: ApplyMealRepository
        ): ApplyMealViewModelFactory =
            ApplyMealViewModelFactory(repository)

}