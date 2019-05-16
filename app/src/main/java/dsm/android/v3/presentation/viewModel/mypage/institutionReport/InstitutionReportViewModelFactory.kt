package dsm.android.v3.presentation.viewModel.mypage.institutionReport

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dsm.android.v3.domain.repository.institutionReport.InstitutionReportRepository

class InstitutionReportViewModelFactory(val repository: InstitutionReportRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(InstitutionReportRepository::class.java).newInstance(repository)
}