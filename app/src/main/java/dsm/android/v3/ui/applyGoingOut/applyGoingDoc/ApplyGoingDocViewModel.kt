package dsm.android.v3.ui.applyGoingOut.applyGoingDoc

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import dsm.android.v3.connecter.api
import dsm.android.v3.util.SingleLiveEvent
import dsm.android.v3.util.getToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class ApplyGoingDocViewModel(application: Application) : AndroidViewModel(application) {

    private val dateFormat = SimpleDateFormat("MM/dd")
    private val sendDateFormat = SimpleDateFormat("MM-dd")
    private val timeFormat = SimpleDateFormat("HH:mm ~ HH:mm")

    val applyGoingGoDate = MutableLiveData<String>()
    val applyGoingGoTime = MutableLiveData<String>()
    val applyGoingReason = MutableLiveData<String>()

    val applyGoingGoDateError = MutableLiveData<String>()
    val applyGoingGoTimeError = MutableLiveData<String>()
    val applyGoingReasonError = MutableLiveData<String>()

    val backApplyGoingSingleLiveEvent = SingleLiveEvent<Any>()
    val createShortToastSingleLiveEvent = SingleLiveEvent<String>()

    init {
        val date = Date(System.currentTimeMillis())
        applyGoingGoDate.value = dateFormat.format(date)
        applyGoingGoTime.value = timeFormat.format(date)
    }

    private fun createDateString(): String = sendDateFormat.format(dateFormat.parse(applyGoingGoDate.value))

    fun applyGoingDocClickApply() {
        if (applyGoingGoDate.value.isNullOrBlank() or !applyGoingGoDate.value!!.matches(Regex("[0-1]\\d/[0-3]\\d")))
            applyGoingGoDateError.value = "MM/DD 포맷에 맞춰 정확한 날짜를 입력해주세요."
        else applyGoingGoDateError.value = null
        if (applyGoingGoTime.value.isNullOrBlank() or !applyGoingGoTime.value!!.matches(Regex("[0-2]\\d:[0-5]\\d\\s~\\s[0-2]\\d:[0-5]\\d")))
            applyGoingGoTimeError.value = "hh:mm ~ hh:mm 포맷에 맞춰 정확한 시간을 입력해주세요."
        else applyGoingGoTimeError.value = null
        if (applyGoingReason.value.isNullOrBlank()) applyGoingReasonError.value = "사유를 입력하세요"
        else applyGoingReasonError.value = null
        if (applyGoingGoDateError.value.isNullOrBlank() and applyGoingGoTimeError.value.isNullOrBlank() and applyGoingReasonError.value.isNullOrBlank()) {
            api.applyGoingOutDoc(
                getToken(getApplication()), hashMapOf(
                    "date" to "${createDateString()} ${applyGoingGoTime.value}"
                    , "reason" to "${applyGoingReason.value}"
                )
            ).enqueue(object : Callback<Unit> {

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    createShortToastSingleLiveEvent.value =
                        when (response.code()) {
                            201 -> "외출신청에 성공했습니다."
                            403 -> "외출신청 권한이 없습니다."
                            409 -> "외출신청 가능시간이 아닙니다."
                            500 -> "로그인이 필요합니다."
                            else -> "오류코드: ${response.code()}"
                        }
                    applyGoingDocClickBack()
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    createShortToastSingleLiveEvent.value = "오류가 발생했습니다."
                }
            })
        }
    }

    fun applyGoingDocClickBack() = backApplyGoingSingleLiveEvent.call()
}