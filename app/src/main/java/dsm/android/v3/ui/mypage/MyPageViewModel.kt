package dsm.android.v3.ui.mypage

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class MyPageViewModel(private val contract: MyPageContract?): ViewModel() {

    private lateinit var LogoutContract: MyPageContract.LogoutContract
    private lateinit var BugReportContract: MyPageContract.BugReportContract
    private lateinit var InstitutionReportContract: MyPageContract.InstitutionReportContract

    var NameText = ObservableField<String>().apply { set("") }
    var InfoText = ObservableField<String>().apply { set("") }
    var MeritText = ObservableField<String>().apply { set("") }
    var DemeritText = ObservableField<String>().apply { set("") }
    var AdviceText = ObservableField<String>().apply { set("") }

    var BugTitleEditText = ObservableField<String>().apply { set("") }
    var BugContentEditText = ObservableField<String>().apply { set("") }

    var InstitutionTitleEditText = ObservableField<String>().apply { set("") }
    var InstitutionRoomNumberEditText = ObservableField<String>().apply { set("") }
    var InstitutionReportContentEditText = ObservableField<String>().apply { set("") }

    constructor (contract: MyPageContract.LogoutContract) : this(contract = null){
        LogoutContract = contract
    }
    constructor (contract: MyPageContract.BugReportContract) : this(contract = null){
        BugReportContract = contract
    }
    constructor (contract: MyPageContract.InstitutionReportContract) : this(contract = null){
        InstitutionReportContract = contract
    }

    fun clickEnterInstitutionReport() = contract?.showDialogInstitutionReport()
    fun clickEnterQuestionResearch() = contract?.intentQuestionResearch()
    fun clickEnterBugReport() = contract?.showDialogBugReport()
    fun clickEnterLogout() = contract?.showDialogLogout()
    fun clickEnterPasswordChange() = contract?.intentPasswordChange()
    fun clickEnterMeritHistory() = contract?.intentMeriteHistory()
    fun clickEnterIntroDevelopers() = contract?.intentintroDevelopers()

    fun bugClickCancel(){

    }
    fun bugClickSend(){

    }

    fun institutionClickCancel(){

    }
    fun institutionClickSend(){

    }

    fun logoutClickCancel(){

    }
    fun logoutClickLogout(){

    }
}