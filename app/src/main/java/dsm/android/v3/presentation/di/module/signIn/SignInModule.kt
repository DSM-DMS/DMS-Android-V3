package dsm.android.v3.presentation.di.module.signIn

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.local.dao.AuthDao
import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.signIn.SignInRepository
import dsm.android.v3.domain.repository.signIn.SignInRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.viewModel.signIn.SignInViewModelFactory

@Module
class SignInModule {
    @ActivityScope
    @Provides
    fun provideRepository(apiClient: ApiClient, localStorage: LocalStorage, authDao: AuthDao): SignInRepository
            = SignInRepositoryImpl(apiClient, localStorage, authDao)

    @ActivityScope
    @Provides
    fun provideViewModelFactory(repository: SignInRepository): SignInViewModelFactory
            = SignInViewModelFactory(repository)
}