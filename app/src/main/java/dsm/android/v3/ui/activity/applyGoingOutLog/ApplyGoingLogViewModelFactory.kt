package dsm.android.v3.ui.activity.applyGoingOutLog

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ApplyGoingLogViewModelFactory(val contract: ApplyGoingLogContract, val title: String): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(ApplyGoingLogContract::class.java, String::class.java).newInstance(contract, title)
}