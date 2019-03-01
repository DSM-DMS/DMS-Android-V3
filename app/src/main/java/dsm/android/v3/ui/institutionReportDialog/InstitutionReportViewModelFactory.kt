package dsm.android.v3.ui.institutionReportDialog

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class InstitutionReportViewModelFactory(val contract: InstitutionReportContract) : ViewModelProvider.Factory {
    
    override fun <T : ViewModel?> create(modelClass: Class<T>): T 
            = modelClass.getConstructor(InstitutionReportContract::class.java).newInstance(contract)
}