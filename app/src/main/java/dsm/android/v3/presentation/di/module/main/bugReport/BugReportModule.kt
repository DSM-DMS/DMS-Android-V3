package dsm.android.v3.presentation.di.module.main.bugReport

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.bugReport.BugReportRepository
import dsm.android.v3.domain.repository.bugReport.BugReportRepositoryImpl
import dsm.android.v3.presentation.di.scope.DialogFragmentScope
import dsm.android.v3.presentation.viewModel.mypage.bugReport.BugReportViewModelFactory

@Module
class BugReportModule {

    @DialogFragmentScope
    @Provides
    fun provideRepository(apiClient: ApiClient): BugReportRepository
            = BugReportRepositoryImpl(apiClient)

    @DialogFragmentScope
    @Provides
    fun provideViewModelFactory(repository: BugReportRepository): BugReportViewModelFactory
            = BugReportViewModelFactory(repository)
}