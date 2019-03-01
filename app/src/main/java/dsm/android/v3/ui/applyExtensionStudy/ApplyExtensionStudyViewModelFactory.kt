package dsm.android.v3.ui.applyExtensionStudy

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ApplyExtensionStudyViewModelFactory(val contract: ApplyExtensionStudyContract): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(ApplyExtensionStudyContract::class.java).newInstance(contract)
}