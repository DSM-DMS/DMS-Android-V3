package dsm.android.v3.ui.bugReportDialog

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import dsm.android.v3.connecter.api
import dsm.android.v3.util.getToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BugReportViewModel(val contract: BugReportContract): ViewModel(){

    val bugTitleEditText = MutableLiveData<String>()
    val bugContentEditText = MutableLiveData<String>()

    fun bugClickCancel() = contract.exitBugReport()

    fun bugClickSend(view: View) {
        if (bugTitleEditText.value.isNullOrBlank())
            contract.flagBugTitleBlankError()
        else if (bugContentEditText.value.isNullOrBlank())
            contract.flagBugContentBlankError()
        else {
            api.reportBug(getToken(view.context), hashMapOf("content" to "${bugTitleEditText.value}/${bugContentEditText.value}"))
                .enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        contract.createShortToast(
                            when (response.code()) {
                                201 -> "버그 신고에 성공했습니다."
                                400 -> "플렛폼 설정 오류입니다."
                                403 -> "버그 신고 권한이 없습니다."
                                else -> "오류 코드: ${response.code()}"
                        })
                        contract.exitBugReport()
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        contract.createShortToast("오류가 발생했습니다.")
                        contract.exitBugReport()
                    }
                })
        }
    }
}