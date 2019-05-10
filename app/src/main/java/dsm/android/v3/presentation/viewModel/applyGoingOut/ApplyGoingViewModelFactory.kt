package dsm.android.v3.presentation.viewModel.applyGoingOut

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dsm.android.v3.domain.repository.applyGoingOut.ApplyGoingOutRepository

class ApplyGoingViewModelFactory(val applyGoingOutRepository: ApplyGoingOutRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            modelClass.getConstructor(ApplyGoingOutRepository::class.java).newInstance(applyGoingOutRepository)
}