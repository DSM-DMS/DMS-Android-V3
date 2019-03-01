package dsm.android.v3.ui.bugReportDialog

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class BugReportViewModelFactory(val contract: BugReportContract) : ViewModelProvider.Factory {
    
    override fun <T : ViewModel?> create(modelClass: Class<T>): T 
            = modelClass.getConstructor(BugReportContract::class.java).newInstance(contract)
}