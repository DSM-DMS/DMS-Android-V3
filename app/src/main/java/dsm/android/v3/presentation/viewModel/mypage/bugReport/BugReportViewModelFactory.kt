package dsm.android.v3.presentation.viewModel.mypage.bugReport

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dsm.android.v3.domain.repository.bugReport.BugReportRepository

class BugReportViewModelFactory(val repository: BugReportRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(BugReportRepository::class.java).newInstance(repository)
}