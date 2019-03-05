package dsm.android.v3.ui.applyGoingDoc

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import dsm.android.v3.connecter.api
import dsm.android.v3.util.getToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class ApplyGoingDocViewModel(val contract: ApplyGoingDocContract): ViewModel(){

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    private val timeFormat = SimpleDateFormat("hh:mm")

    val applyGoingGoDate = MutableLiveData<String>()
    val applyGoingGoTime = MutableLiveData<String>()
    val applyGoingReturnDate = MutableLiveData<String>()
    val applyGoingReturnTime = MutableLiveData<String>()
    val applyGoingReason = MutableLiveData<String>()

    init {
        val date = Date(System.currentTimeMillis())
        applyGoingGoDate.value = dateFormat.format(date)
        applyGoingReturnDate.value = dateFormat.format(date)
        applyGoingGoTime.value = timeFormat.format(date)
        applyGoingReturnTime.value = timeFormat.format(date)
    }

    fun applyGoingDocClickApply(view: View){
        if(applyGoingGoDate.value.isNullOrBlank()) contract.setErrorApplyGoingGoDate()
        else if(applyGoingGoTime.value.isNullOrBlank()) contract.setErrorApplyGoingGoTime()
        else if(applyGoingReturnDate.value.isNullOrBlank()) contract.setErrorApplyGoingBackDate()
        else if(applyGoingReturnTime.value.isNullOrBlank()) contract.setErrorApplyGoingBackTime()
        else if (applyGoingReason.value.isNullOrBlank()) contract.setErrorApplyGoingReason()

        else {
            api.applyGoingOutDoc(getToken(view.context), hashMapOf(
                "goOutDate" to "${applyGoingGoDate.value} ${applyGoingGoTime.value}"
                , "returnDate" to "${applyGoingReturnDate.value} ${applyGoingReturnTime.value}"
                , "reason" to "${applyGoingReason.value}")).enqueue(object: Callback<Unit>{

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    contract.createShortToast(
                        when(response.code()){
                            201 -> "외출신청에 성공했습니다."
                            204 -> "외출신청 가능시간이 아닙니다."
                            403 -> "외출신청 권한이 없습니다."
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

    fun applyGoingDocClickBack() = contract.backApplyGoing()
}