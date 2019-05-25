package dsm.android.v3.presentation.di.module.register

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.register.RegisterRepository
import dsm.android.v3.domain.repository.register.RegisterRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.viewModel.register.RegisterViewModelFactory

@Module
class RegisterModule {

    @ActivityScope
    @Provides
    fun provideRepository(apiClient: ApiClient): RegisterRepository
            = RegisterRepositoryImpl(apiClient)

    @ActivityScope
    @Provides
    fun provideViewModelFactory(repository: RegisterRepository): RegisterViewModelFactory
            = RegisterViewModelFactory(repository)
}