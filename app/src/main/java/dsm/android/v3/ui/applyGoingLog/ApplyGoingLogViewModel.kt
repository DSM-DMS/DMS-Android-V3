package dsm.android.v3.ui.applyGoingLog

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import dsm.android.v3.connecter.api
import dsm.android.v3.ui.applyGoingLog.ApplyGoingLogData.deleteDataList
import dsm.android.v3.util.getToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplyGoingLogViewModel(val contract: ApplyGoingLogContract, val title: String): ViewModel() {

    val logTitle = MutableLiveData<String>()

    init {
        logTitle.value = title

        when(logTitle.value){
            "토요외출" -> contract.setApplyList(ApplyGoingLogData.saturdayItemList)
            "일요외출" -> contract.setApplyList(ApplyGoingLogData.sundayItemList)
            "평일외출" -> contract.setApplyList(ApplyGoingLogData.workdayItemList)
        }
    }

    fun applyGoingClickDelete(view: View) {
        for(deleteData in deleteDataList){
            api.deleteGoingOut(getToken(view.context), hashMapOf("applyId" to deleteData.id)).enqueue(object: Callback<Unit>{
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
    }

    fun applyGoingLogClickBack() {
        contract.backApplyGoing()
    }
}