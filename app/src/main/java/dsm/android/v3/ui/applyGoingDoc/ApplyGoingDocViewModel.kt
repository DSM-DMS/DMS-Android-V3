package dsm.android.v3.ui.applyGoingDoc

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import dsm.android.v3.connecter.api
import dsm.android.v3.util.getToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class ApplyGoingDocViewModel(val contract: ApplyGoingDocContract): ViewModel(){

    private val dateFormat = SimpleDateFormat("MM/dd")
    private val sendDateFormat = SimpleDateFormat("MM-dd")
    private val timeFormat = SimpleDateFormat("HH:mm ~ HH:mm")

    val applyGoingGoDate = MutableLiveData<String>()
    val applyGoingGoTime = MutableLiveData<String>()
    val applyGoingReason = MutableLiveData<String>()

    init {
        val date = Date(System.currentTimeMillis())
        applyGoingGoDate.value = dateFormat.format(date)
        applyGoingGoTime.value = timeFormat.format(date)
    }

    private fun createDateString(): String = sendDateFormat.format(dateFormat.parse(applyGoingGoDate.value))

    fun applyGoingDocClickApply(view: View){
        if(applyGoingGoDate.value.isNullOrBlank()) contract.setErrorApplyGoingGoDate()
        else if(applyGoingGoTime.value.isNullOrBlank()) contract.setErrorApplyGoingGoTime()
        else if (applyGoingReason.value.isNullOrBlank()) contract.setErrorApplyGoingReason()

        else {
            api.applyGoingOutDoc(getToken(view.context), hashMapOf(
                "date" to "${createDateString()} ${applyGoingGoTime.value}"
                , "reason" to "${applyGoingReason.value}")).enqueue(object: Callback<Unit>{

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    contract.createShortToast(
                        when(response.code()){
                            201 -> "외출신청에 성공했습니다."
                            403 -> "외출신청 권한이 없습니다."
                            409 -> "외출신청 가능시간이 아닙니다."
                            500 -> "로그인이 필요합니다."
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

    fun onDateTextChanged(){
        Log.d("applyGoingGoTime", applyGoingGoDate.value!!.length.toString())
        if (applyGoingGoDate.value!!.length == 2)
            applyGoingGoDate.value += "/"
    }

    fun onTimeTextChanged(){
        Log.d("applyGoingGoTime", applyGoingGoTime.value!!.length.toString())
        if (applyGoingGoTime.value!!.length == 2 || applyGoingGoTime.value!!.length == 10){
            applyGoingGoTime.value += ":"
        }
        else if (applyGoingGoTime.value!!.length == 5)
            applyGoingGoTime.value += " ~ "

    }

    fun applyGoingDocClickBack() = contract.backApplyGoing()
}