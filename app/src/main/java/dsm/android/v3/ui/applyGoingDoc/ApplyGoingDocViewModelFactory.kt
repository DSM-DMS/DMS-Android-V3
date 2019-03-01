package dsm.android.v3.ui.applyGoingDoc

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dsm.android.v3.ui.applyGoing.ApplyGoingContract

class ApplyGoingDocViewModelFactory(val contract: ApplyGoingDocContract): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(ApplyGoingDocContract::class.java).newInstance(contract)
}
