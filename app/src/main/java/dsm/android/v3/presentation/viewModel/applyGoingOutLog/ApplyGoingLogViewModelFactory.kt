package dsm.android.v3.presentation.viewModel.applyGoingOutLog

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dsm.android.v3.domain.repository.applyGoingOut.ApplyGoingOutRepository
import dsm.android.v3.domain.repository.pointLog.PointLogRepository

class ApplyGoingLogViewModelFactory(val title: String, val applyGoingOutRepository: ApplyGoingOutRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(String::class.java, ApplyGoingOutRepository::class.java).newInstance(title, applyGoingOutRepository)
}