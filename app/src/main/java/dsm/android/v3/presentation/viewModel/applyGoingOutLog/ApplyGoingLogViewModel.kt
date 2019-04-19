package dsm.android.v3.presentation.viewModel.applyGoingOutLog

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import dsm.android.v3.ui.activity.applyGoingOutLog.ApplyGoingLogContract
import dsm.android.v3.ui.activity.applyGoingOutLog.ApplyGoingLogData

class ApplyGoingLogViewModel(val contract: ApplyGoingLogContract, title: String): ViewModel() {

    val logTitle = MutableLiveData<String>()

    init {
        logTitle.value = title

        when(logTitle.value){
            "토요외출" -> contract.setApplyList(ApplyGoingLogData.saturdayItemList)
            "일요외출" -> contract.setApplyList(ApplyGoingLogData.sundayItemList)
            "평일외출" -> contract.setApplyList(ApplyGoingLogData.workdayItemList)
        }
    }
}