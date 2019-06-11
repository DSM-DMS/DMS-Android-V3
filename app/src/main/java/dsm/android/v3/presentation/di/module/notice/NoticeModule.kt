package dsm.android.v3.presentation.di.module.notice

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dsm.android.v3.data.local.dao.OfflineDao
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.notice.NoticeRepository
import dsm.android.v3.domain.repository.notice.NoticeRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.ui.fragment.notice.NoticeDescriptionFragment

@Module
abstract class NoticeModule {

    @Module
    companion object {
        @ActivityScope
        @JvmStatic
        @Provides
        fun provideRepository(apiClient: ApiClient, dao: OfflineDao): NoticeRepository
                = NoticeRepositoryImpl(apiClient, dao)
    }


    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun noticeDescriptionFragment(): NoticeDescriptionFragment
}