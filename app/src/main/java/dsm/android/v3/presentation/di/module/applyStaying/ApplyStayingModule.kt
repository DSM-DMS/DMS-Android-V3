package dsm.android.v3.presentation.di.module.applyStaying

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.local.dao.OfflineDao
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.applyStaying.ApplyStayingRepository
import dsm.android.v3.domain.repository.applyStaying.ApplyStayingRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.viewModel.applyStaying.ApplyStayingViewModelFactory

@Module
class ApplyStayingModule {
    @ActivityScope
    @Provides
    fun provideRepository(apiClient: ApiClient, dao: OfflineDao): ApplyStayingRepository
            = ApplyStayingRepositoryImpl(apiClient, dao)

    @ActivityScope
    @Provides
    fun provideViewModelFactory(repository: ApplyStayingRepository): ApplyStayingViewModelFactory
            = ApplyStayingViewModelFactory(repository)
}