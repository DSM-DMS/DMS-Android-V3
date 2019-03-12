package dsm.android.v3.ui.applyGoingEdit

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import dsm.android.v3.connecter.api
import dsm.android.v3.ui.applyGoingLog.ApplyGoingLogData.deleteItem
import dsm.android.v3.util.getToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class ApplyGoingEditViewModel(val contract: ApplyGoingEditContract): ViewModel(){

    private val dateFormat = SimpleDateFormat("MM/dd")
    private val sendDateFormat = SimpleDateFormat("MM-dd")
    private val timeFormat = SimpleDateFormat("HH:mm ~ HH:mm")

    val applyGoingGoDate = MutableLiveData<String>()
    val applyGoingGoTime = MutableLiveData<String>()
    val applyGoingReason = MutableLiveData<String>()

    init { setDataText() }

    private fun setDataText(){
        val idx = deleteItem.date.indexOf(" ")
        applyGoingGoDate.value = createSetDateString(deleteItem.date.substring(0..idx-1))
        applyGoingGoTime.value = deleteItem.date.substring(idx+1)
        applyGoingReason.value = deleteItem.reason
    }

    private fun createSendDateString(date: String): String = sendDateFormat.format(dateFormat.parse(date))
    private fun createSetDateString(date: String): String = dateFormat.format(sendDateFormat.parse(date))

    fun applyGoingEditClickCancel(view: View){
        api.deleteGoingOut(getToken(view.context), hashMapOf("applyId" to deleteItem.id)).enqueue(object: Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                contract.createShortToast(when(response.code()){
                    200 -> "외출신청 취소에 성공했습니다."
                    204 -> "존재하지 않는 외출신청입니다."
                    else -> "오류코드: ${response.code()}"
                })
                contract.backApplyGoing()
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                contract.createShortToast("오류가 발생했습니다.")
            }
        })
    }

   fun applyGoingEditClickEdit(view: View){
        if(applyGoingGoDate.value.isNullOrBlank()) contract.setErrorApplyGoingGoDate()
        else if(applyGoingGoTime.value.isNullOrBlank()) contract.setErrorApplyGoingGoTime()
        else if (applyGoingReason.value.isNullOrBlank()) contract.setErrorApplyGoingReason()

        else {
            api.editGoingOut(
                getToken(view.context), hashMapOf(
                    "applyId" to deleteItem.id
                    , "date" to "${createSendDateString(applyGoingGoDate.value!!)} ${applyGoingGoTime.value}"
                    , "reason" to "${applyGoingReason.value}")).enqueue(object: Callback<Unit> {

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    contract.createShortToast(
                        when(response.code()){
                            201 -> "외출신청 수정에 성공했습니다."
                            204 -> "외출신청 수정 가능시간이 아닙니다."
                            403 -> "외출신청 수정 권한이 없습니다."
                            else -> "오류코드: ${response.code()}"
                        })
                    contract.backApplyGoing()
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    contract.createShortToast("오류가 발생했습니다.")
                }
            })
        }
    }

    fun applyGoingEditClickBack() = contract.backApplyGoing()
}