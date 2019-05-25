package dsm.android.v3.presentation.viewModel.mypage.institutionReport

import android.arch.lifecycle.MutableLiveData
import android.view.View
import dsm.android.v3.domain.repository.institutionReport.InstitutionReportRepository
import dsm.android.v3.util.BaseViewModel
import dsm.android.v3.util.SingleLiveEvent

class InstitutionReportViewModel(val institutionReportRepository: InstitutionReportRepository): BaseViewModel(){

    val institutionTitle = MutableLiveData<String>()
    val institutionRoomNumber = MutableLiveData<String>()
    val institutionReportContent = MutableLiveData<String>()

    val institutionTitleError = MutableLiveData<String>()
    val institutionRoomNumberError = MutableLiveData<String>()
    val institutionReportContentError = MutableLiveData<String>()

    val toastLiveData = MutableLiveData<String>()
    val exitInstitutionReportEvent = SingleLiveEvent<Any>()

    fun institutionClickSend(view: View){
        if (institutionTitle.value.isNullOrBlank()) institutionTitleError.value = "제목을 입력해주세요."
        else institutionTitleError.value = null
        if (institutionRoomNumber.value.isNullOrBlank()) institutionRoomNumberError.value = "방 번호를 입력해주세요."
        else institutionRoomNumberError.value = null
        if (institutionReportContent.value.isNullOrBlank()) institutionReportContentError.value = "내용을 입력해주세요."
        else institutionReportContentError.value = null
        if (institutionTitleError.value.isNullOrBlank() and institutionRoomNumberError.value.isNullOrBlank() and institutionReportContentError.value.isNullOrBlank()){
            add(institutionReportRepository.reportInstitution(hashMapOf("room" to institutionRoomNumber.value!!.toInt()
                , "content" to "${institutionTitle.value}/${institutionReportContent.value}"))
                .subscribe({ response ->
                    toastLiveData.value =
                        when(response.code()){
                            201 -> "시설고장 신고에 성공했습니다."
                            403 -> "시설고장 신고 권한이 없습니다."
                            else -> "오류코드: ${response.code()}"
                        }
                    exitInstitutionReportEvent.call()
                }, {
                    toastLiveData.value = "오류가 발생했습니다."
                    exitInstitutionReportEvent.call()
                }))
        }
    }

    fun institutionClickCancel() = exitInstitutionReportEvent.call()
}