package dsm.android.v3.ui.applyMusic

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ApplyMusicViewModelFactory(val contract: ApplyMusicContract): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(ApplyMusicContract::class.java).newInstance(contract)
}