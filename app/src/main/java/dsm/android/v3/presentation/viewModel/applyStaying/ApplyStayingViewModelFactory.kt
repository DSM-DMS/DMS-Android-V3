package dsm.android.v3.presentation.viewModel.applyStaying

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dsm.android.v3.domain.repository.applyStaying.ApplyStayingRepository

class ApplyStayingViewModelFactory(val applyStayingRepository: ApplyStayingRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            modelClass.getConstructor(ApplyStayingRepository::class.java).newInstance(applyStayingRepository)
}