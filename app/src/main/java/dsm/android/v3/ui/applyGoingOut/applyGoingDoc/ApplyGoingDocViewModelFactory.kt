package dsm.android.v3.ui.applyGoingOut.applyGoingDoc

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ApplyGoingDocViewModelFactory(val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(Application::class.java).newInstance(application)
}