package dsm.android.v3.ui.activity.applyGoingOutEdit

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ApplyGoingEditViewModelFactory(val contract: ApplyGoingEditContract): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(ApplyGoingEditContract::class.java).newInstance(contract)
}