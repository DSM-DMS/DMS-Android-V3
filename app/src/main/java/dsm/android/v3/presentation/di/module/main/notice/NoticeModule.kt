package dsm.android.v3.presentation.di.module.main.notice

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.meal.MealRepository
import dsm.android.v3.domain.repository.meal.MealRepositoryImpl
import dsm.android.v3.domain.repository.notice.NoticeRepository
import dsm.android.v3.domain.repository.notice.NoticeRepositoryImpl
import dsm.android.v3.presentation.di.scope.DialogFragmentScope
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.presentation.viewModel.meal.MealViewModelFactory
import dsm.android.v3.ui.fragment.notice.NoticeFragment

@Module
abstract class NoticeModule {
    @FragmentScope
    @Provides
    fun provideRepository(apiClient: ApiClient): NoticeRepository
            = NoticeRepositoryImpl(apiClient)

    @DialogFragmentScope
    @ContributesAndroidInjector
    abstract fun noticeDialogFragment(): NoticeFragment
}