package dsm.android.v3.presentation.di.module.changePassword

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.applyGoingOut.ApplyGoingOutRepository
import dsm.android.v3.domain.repository.applyGoingOut.ApplyGoingOutRepositoryImpl
import dsm.android.v3.domain.repository.changePassword.ChangePasswordRepository
import dsm.android.v3.domain.repository.changePassword.ChangePasswordRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.viewModel.applyGoingOut.ApplyGoingViewModelFactory
import dsm.android.v3.presentation.viewModel.changePassword.ChangePasswordViewModelFactory

@Module
class ChangePasswordModule {
    @ActivityScope
    @Provides
    fun provideRepository(apiClient: ApiClient): ChangePasswordRepository
            = ChangePasswordRepositoryImpl(apiClient)

    @ActivityScope
    @Provides
    fun provideViewModelFactory(repository: ChangePasswordRepository): ChangePasswordViewModelFactory
            = ChangePasswordViewModelFactory(repository)
}