package dsm.android.v3.ui.applyMusicDialog

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ApplyMusicDialogViewModelFactory(val  contract: ApplyMusicDialogContract): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
        = modelClass.getConstructor(ApplyMusicDialogContract::class.java).newInstance(contract)
}