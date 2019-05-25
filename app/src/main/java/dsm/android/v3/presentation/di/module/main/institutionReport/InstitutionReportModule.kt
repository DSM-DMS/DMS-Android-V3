package dsm.android.v3.presentation.di.module.main.institutionReport

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.institutionReport.InstitutionReportRepository
import dsm.android.v3.domain.repository.institutionReport.InstitutionReportRepositoryImpl
import dsm.android.v3.presentation.di.scope.DialogFragmentScope
import dsm.android.v3.presentation.viewModel.mypage.institutionReport.InstitutionReportViewModelFactory

@Module
class InstitutionReportModule {
    @DialogFragmentScope
    @Provides
    fun provideRepository(apiClient: ApiClient): InstitutionReportRepository
            = InstitutionReportRepositoryImpl(apiClient)

    @DialogFragmentScope
    @Provides
    fun provideViewModelFactory(repository: InstitutionReportRepository): InstitutionReportViewModelFactory
            = InstitutionReportViewModelFactory(repository)
}