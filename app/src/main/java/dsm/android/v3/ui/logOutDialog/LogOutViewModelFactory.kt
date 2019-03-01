package dsm.android.v3.ui.logOutDialog

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class LogOutViewModelFactory(val contract: LogOutContract) : ViewModelProvider.Factory {
    
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
            = modelClass.getConstructor(LogOutContract::class.java).newInstance(contract)
}