package dsm.android.v3.ui.applyGoingLog

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dsm.android.v3.ui.applyGoing.ApplyGoingContract

class ApplyGoingLogViewModelFactory(val contract: ApplyGoingLogContract, val title: String): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(ApplyGoingLogContract::class.java, String::class.java).newInstance(contract, title)
}