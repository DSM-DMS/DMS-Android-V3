package dsm.android.v3.ui.applyStaying

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders

class ApplyStayingViewModelFactory(val contract: ApplyStayingContract): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ApplyStayingContract::class.java).newInstance(contract)
    }
}