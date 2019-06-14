package dsm.android.v3.presentation.di.module.main.logoutModule

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.domain.repository.logout.LogoutRepository
import dsm.android.v3.domain.repository.logout.LogoutRepositoryImpl
import dsm.android.v3.presentation.di.scope.DialogFragmentScope
import dsm.android.v3.presentation.viewModel.mypage.logout.LogoutViewModelFactory

@Module
class LogoutModule {
    @DialogFragmentScope
    @Provides
    fun provideRepository(localStorage: LocalStorage): LogoutRepository
            = LogoutRepositoryImpl(localStorage)

    @DialogFragmentScope
    @Provides
    fun provideViewModelFactory(repository: LogoutRepository): LogoutViewModelFactory
            = LogoutViewModelFactory(repository)
}