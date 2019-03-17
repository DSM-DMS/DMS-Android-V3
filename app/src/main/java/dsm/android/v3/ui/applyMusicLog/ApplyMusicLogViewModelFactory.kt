package dsm.android.v3.ui.applyMusicLog

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ApplyMusicLogViewModelFactory(val contract: ApplyMusicLogContract): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
        = modelClass.getConstructor(ApplyMusicLogContract::class.java).newInstance(contract)
}