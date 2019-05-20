package dsm.android.v3.presentation.di.module.applyExtensionStudy

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.applyExtensionStudy.ApplyExtensionStudyRepository
import dsm.android.v3.domain.repository.applyExtensionStudy.ApplyExtensionStudyRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.viewModel.applyExtensionStudy.ApplyExtensionStudyViewModelFactory

@Module
class ApplyExtensionStudyModule {

    @ActivityScope
    @Provides
    fun provideRepository(apiClient: ApiClient): ApplyExtensionStudyRepository
            = ApplyExtensionStudyRepositoryImpl(apiClient)

    @ActivityScope
    @Provides
    fun provideViewModelFactory(repository: ApplyExtensionStudyRepository): ApplyExtensionStudyViewModelFactory
            = ApplyExtensionStudyViewModelFactory(repository)
}