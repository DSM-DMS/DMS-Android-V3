package dsm.android.v3.presentation.di.module.applyMeal

import android.app.Application
import dagger.Module
import dagger.Provides
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.applyMeal.ApplyMealRepository
import dsm.android.v3.domain.repository.applyMeal.ApplyMealRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.viewModel.applyMeal.ApplyMealViewModelFactory

@Module
class ApplyMealModule {

    @Module
    companion object {
        @ActivityScope
        @JvmStatic
        @Provides
        fun provideRepository(apiClient: ApiClient): ApplyMealRepositoryImpl =
            ApplyMealRepositoryImpl(apiClient)

        @ActivityScope
        @JvmStatic
        @Provides
        fun provideViewModelFactory(
            repository: ApplyMealRepository,
            application: Application
        ): ApplyMealViewModelFactory =
            ApplyMealViewModelFactory(repository, application)
    }
}