package dsm.android.v3.presentation.viewModel.mypage.bugReport

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import dsm.android.v3.data.remote.api
import dsm.android.v3.util.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BugReportViewModel(): ViewModel(){

    val bugTitleEditText = MutableLiveData<String>()
    val bugContentEditText = MutableLiveData<String>()

    val bugTitleError = MutableLiveData<String>()
    val bugContentError = MutableLiveData<String>()

    val toastLiveData = MutableLiveData<String>()
    val exitBugReportEvent = SingleLiveEvent<Any>()

    fun bugClickSend(view: View) {
        if (bugTitleEditText.value.isNullOrBlank()) bugTitleError.value = "제목을 입력하세요."
        else bugTitleError.value = null
        if (bugContentEditText.value.isNullOrBlank()) bugContentError.value = "내용을 입력하세요"
        else bugContentError.value = null
        if (bugTitleError.value.isNullOrBlank() and bugContentError.value.isNullOrBlank()) {
            api.reportBug(hashMapOf("content" to "${bugTitleEditText.value}/${bugContentEditText.value}"))
                .enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        toastLiveData.value =
                            when (response.code()) {
                                201 -> "버그 신고에 성공했습니다."
                                400 -> "플렛폼 설정 오류입니다."
                                403 -> "버그 신고 권한이 없습니다."
                                else -> "오류 코드: ${response.code()}"
                        }
                        exitBugReportEvent.call()
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        toastLiveData.value = "오류가 발생했습니다."
                        exitBugReportEvent.call()
                    }
                })
        }
    }

    fun bugClickCancel() = exitBugReportEvent.call()
}