package dsm.android.v3.presentation.di.module.main.notice

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.notice.NoticeRepository
import dsm.android.v3.domain.repository.notice.NoticeRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.presentation.viewModel.notice.NoticeViewModelFactory

@Module
class NoticeModule {

    @FragmentScope
    @Provides
    fun provideRepository(apiClient: ApiClient): NoticeRepository = NoticeRepositoryImpl(apiClient)

    @FragmentScope
    @Provides
    fun provideViewModelRepository(repository: NoticeRepository) = NoticeViewModelFactory(repository)
}