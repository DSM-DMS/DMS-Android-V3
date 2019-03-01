package dsm.android.v3.ui.institutionReportDialog

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.view.View
import dsm.android.v3.connecter.api
import dsm.android.v3.util.getToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InstitutionReportViewModel(val contract: InstitutionReportContract): ViewModel(){

    val institutionTitle = MutableLiveData<String>()
    val institutionRoomNumber = MutableLiveData<String>()
    val institutionReportContent = MutableLiveData<String>()

    fun institutionClickCancel() = contract.exitInstitutionReport()

    fun institutionClickSend(view: View){
        if (institutionTitle.value.isNullOrBlank())
            contract.flagInstitutionTitleBlankError()

        else if (institutionRoomNumber.value.isNullOrBlank())
            contract.flagInstitutionRoomNumberBlankError()

        else if (institutionReportContent.value.isNullOrBlank())
            contract.flagInstitutionContentBlankError()

        else{
            api.reportInstitution(getToken(view.context), hashMapOf("room" to institutionRoomNumber.value!!.toInt(), "content" to institutionReportContent.value))
                .enqueue(object: Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        contract.createShortToast(
                            when(response.code()){
                                201 -> "시설고장 신고에 성공했습니다."
                                403 -> "시설고장 신고 권한이 없습니다."
                                else -> "오류코드: ${response.code()}"
                            }
                        )
                        contract.exitInstitutionReport()
                    }
                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        contract.createShortToast("오류가 발생했습니다.")
                        contract.exitInstitutionReport()
                    }
                })
        }
    }
}