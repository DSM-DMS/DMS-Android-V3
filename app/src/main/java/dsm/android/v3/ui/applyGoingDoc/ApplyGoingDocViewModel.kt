package dsm.android.v3.ui.applyGoingDoc

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import dsm.android.v3.model.ApplyGoingLogItemModel
import dsm.android.v3.ui.applyGoing.ApplyGoingContract
import dsm.android.v3.ui.applyGoingLog.ApplyGoingLogData

class ApplyGoingDocViewModel(val contract: ApplyGoingDocContract, val currentItem: Int): ViewModel(){

    val applyGoingGoDate = MutableLiveData<String>()
    val applyGoingGoTime = MutableLiveData<String>()
    val applyGoingReturnDate = MutableLiveData<String>()
    val applyGoingReturnTime = MutableLiveData<String>()
    val applyGoingReason = MutableLiveData<String>()
    val applyGoingWith = MutableLiveData<String>()

    fun applyGoingDocClickApply(){
        if(applyGoingGoDate.value.isNullOrBlank()){
            contract.setErrorApplyGoingGoDate()
        }
        else if(applyGoingGoTime.value.isNullOrBlank()){
            contract.setErrorApplyGoingGoTime()
        }
        else if(applyGoingReturnDate.value.isNullOrBlank()){
            contract.setErrorApplyGoingBackDate()
        }
        else if(applyGoingReturnTime.value.isNullOrBlank()){
            contract.setErrorApplyGoingBackTime()
        }
        else if (applyGoingReason.value.isNullOrBlank()){
            contract.setErrorApplyGoingReason()
        }
        // 동행인 없어도 됨 나중에 서버에 넘겨줄 때 없이 넘겨주면 됨
        else {
            // 서버 통신 필요
            when(currentItem){
                0 -> {
                    if (ApplyGoingLogData.saturdayItemList.size != 5){
                        ApplyGoingLogData.saturdayItemList.add(
                            ApplyGoingLogItemModel(
                                "${applyGoingGoDate.value} ${applyGoingGoTime.value} ~ ${applyGoingReturnTime.value}", "${applyGoingReason.value}")
                        )
                    } else {
                        contract.createListFullWarningToast()
                    }
                }
                1 -> {
                    if (ApplyGoingLogData.sundayItemList.size != 5){
                        ApplyGoingLogData.sundayItemList.add(
                            ApplyGoingLogItemModel(
                                "${applyGoingGoDate.value} ${applyGoingGoTime.value} ~ ${applyGoingReturnTime.value}", "${applyGoingReason.value}")
                        )
                    } else {
                        contract.createListFullWarningToast()
                    }
                }
                2 -> {
                    if (ApplyGoingLogData.workdayItemList.size != 5){
                        ApplyGoingLogData.workdayItemList.add(
                            ApplyGoingLogItemModel(
                                "${applyGoingGoDate.value} ${applyGoingGoTime.value} ~ ${applyGoingReturnTime.value}", "${applyGoingReason.value}")
                        )
                    } else {
                        contract.createListFullWarningToast()
                    }
                }
            }
            contract.backApplyGoing()
        }
    }

    fun applyGoingDocClickBack() {
        contract.backApplyGoing()
    }

}