package dsm.android.v3.ui.mypage

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class MyPageViewModel(private val contract: MyPageContract?): ViewModel() {

    private lateinit var LogoutContract: MyPageContract.LogoutContract
    private lateinit var BugReportContract: MyPageContract.BugReportContract
    private lateinit var InstitutionReportContract: MyPageContract.InstitutionReportContract

    private val _nameText = MutableLiveData<String>()
    private val _infoText = MutableLiveData<String>()
    private val _meritText = MutableLiveData<String>()
    private val _demeritText = MutableLiveData<String>()
    private val _adviceText = MutableLiveData<String>()

    val nameText: LiveData<String> get() = _nameText
    val infoText: LiveData<String> get() = _infoText
    val meritText : LiveData<String> get() = _meritText
    val demeritText : LiveData<String> get() = _demeritText
    val adviceText : LiveData<String> get() = _adviceText

    private val _bugTitleEditText = MutableLiveData<String>()
    private val _bugContentEditText = MutableLiveData<String>()

    val bugTitleEditText : LiveData<String> get() = _bugTitleEditText
    val bugContentEditText : LiveData<String> get() = _bugContentEditText

    private val _institutionTitleEditText = MutableLiveData<String>()
    private val _institutionRoomNumberEditText = MutableLiveData<String>()
    private val _institutionReportContentEditText = MutableLiveData<String>()

    val institutionTitleEditText : LiveData<String> get() = _institutionTitleEditText
    val institutionRoomNumberEditText : LiveData<String> get() = _institutionRoomNumberEditText
    val institutionReportContentEditText : LiveData<String> get() = _institutionReportContentEditText


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