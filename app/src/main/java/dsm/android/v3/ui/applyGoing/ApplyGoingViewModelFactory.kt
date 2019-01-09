package dsm.android.v3.ui.applyGoing

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ApplyGoingViewModelFactory(private var applyGoingContract: ApplyGoingContract?): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(ApplyGoingContract::class.java).newInstance(applyGoingContract)
}