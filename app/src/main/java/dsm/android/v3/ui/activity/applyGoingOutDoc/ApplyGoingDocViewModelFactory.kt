package dsm.android.v3.ui.activity.applyGoingOutDoc

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ApplyGoingDocViewModelFactory(val contract: ApplyGoingDocContract): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(ApplyGoingDocContract::class.java).newInstance(contract)
}
