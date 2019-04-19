package dsm.android.v3.ui.activity.applyGoingOut

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ApplyGoingViewModelFactory(val contract: ApplyGoingContract): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(ApplyGoingContract::class.java).newInstance(contract)
}