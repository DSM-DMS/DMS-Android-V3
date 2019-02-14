package dsm.android.v3.ui.applyGoingLog

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

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

    fun applyGoingClickDelete() {
        // 서버 통신 필요
        when(logTitle.value){
            "토요외출" -> ApplyGoingLogData.saturdayItemList.removeAll(ApplyGoingLogData.deleteData)
            "일요외출" -> ApplyGoingLogData.sundayItemList.removeAll(ApplyGoingLogData.deleteData)
            "평일외출" -> ApplyGoingLogData.workdayItemList.removeAll(ApplyGoingLogData.deleteData)
        }
        contract.backApplyGoing()
    }

    fun applyGoingLogClickBack() {
        contract.backApplyGoing()
    }
}