package dsm.android.v3.ui.applyGoing

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ApplyGoingViewModelFactory(private var applyGoingContract: ApplyGoingContract?): ViewModelProvider.Factory{

    private var applyGoingLogContract: ApplyGoingContract.ApplyGoingLogContract? = null
    private var applyGoingDocContract: ApplyGoingContract.ApplyGoingDocContract? = null
    private var title: String? = null

    constructor(contract: ApplyGoingContract.ApplyGoingLogContract, title: String): this(applyGoingContract = null){
        applyGoingLogContract = contract
        this.title = title
    }

    constructor(contract: ApplyGoingContract.ApplyGoingDocContract): this(applyGoingContract = null){
        applyGoingDocContract = contract
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (applyGoingContract != null) return modelClass.getConstructor(ApplyGoingContract::class.java).newInstance(applyGoingContract)
        else if(applyGoingLogContract != null) return modelClass.getConstructor(ApplyGoingContract.ApplyGoingLogContract::class.java, String::class.java).newInstance(applyGoingLogContract, title)
        else return modelClass.getConstructor(ApplyGoingContract.ApplyGoingDocContract::class.java).newInstance(applyGoingDocContract)
    }
}