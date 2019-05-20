package dsm.android.v3.presentation.viewModel.mypage.logout

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dsm.android.v3.domain.repository.logout.LogoutRepository

class LogoutViewModelFactory(val repository: LogoutRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(LogoutRepository::class.java).newInstance(repository)
}