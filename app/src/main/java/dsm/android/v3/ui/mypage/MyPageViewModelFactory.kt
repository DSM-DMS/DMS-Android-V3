package dsm.android.v3.ui.mypage

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class MyPageViewModelFactory(val contract: MyPageContract?) : ViewModelProvider.Factory {

    private var logoutContract: MyPageContract.LogoutContract? = null
    private var bugContract: MyPageContract.BugReportContract? = null
    private var institutionContract: MyPageContract.InstitutionReportContract? = null

    constructor(contract: MyPageContract.LogoutContract): this(contract = null){
       logoutContract = contract
    }
    constructor(contract: MyPageContract.BugReportContract): this(contract = null){
        bugContract = contract
    }
    constructor(contract: MyPageContract.InstitutionReportContract): this(contract = null){
        institutionContract = contract
    }
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(logoutContract != null)
            return modelClass.getConstructor(MyPageContract.LogoutContract::class.java).newInstance(logoutContract)
        else if(bugContract != null)
            return modelClass.getConstructor(MyPageContract.BugReportContract::class.java).newInstance(bugContract)
        else if(institutionContract != null)
            return modelClass.getConstructor(MyPageContract.InstitutionReportContract::class.java).newInstance(institutionContract)
        else(contract != null)
            return modelClass.getConstructor(MyPageContract::class.java).newInstance(contract)
    }
}