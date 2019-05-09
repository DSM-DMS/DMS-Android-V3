package dsm.android.v3.presentation.viewModelFactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ApplyGoingLogViewModelFactory(val title: String): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(String::class.java).newInstance(title)
}