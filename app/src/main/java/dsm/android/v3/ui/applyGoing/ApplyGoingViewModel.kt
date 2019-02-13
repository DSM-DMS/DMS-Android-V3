package dsm.android.v3.ui.applyGoing

import android.arch.lifecycle.*
import dsm.android.v3.model.ApplyGoingLogItemModel
import dsm.android.v3.ui.applyGoing.ApplyGoingLogData.deleteData
import dsm.android.v3.ui.applyGoing.ApplyGoingLogData.saturdayItemList
import dsm.android.v3.ui.applyGoing.ApplyGoingLogData.sundayItemList
import dsm.android.v3.ui.applyGoing.ApplyGoingLogData.workdayItemList
import dsm.android.v3.util.LifecycleCallback

class ApplyGoingViewModel(): ViewModel(), LifecycleCallback{

    private var applyGoingContract: ApplyGoingContract? = null
    private var applyGoingLogContract: ApplyGoingContract.ApplyGoingLogContract? = null
    private var applyGoingDocContract: ApplyGoingContract.ApplyGoingDocContract? = null

    val nowCurrentItem = MutableLiveData<Int>()
    val logTitle = MutableLiveData<String>()

    val applyGoingGoDate = MutableLiveData<String>()
    val applyGoingGoTime = MutableLiveData<String>()
    val applyGoingReturnDate = MutableLiveData<String>()
    val applyGoingReturnTime = MutableLiveData<String>()
    val applyGoingReason = MutableLiveData<String>()
    val applyGoingWith = MutableLiveData<String>()

    override fun apply(event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_START -> {
                deleteData.clear()
                settingViewPager()
            }
        }
    }

    constructor(contract: ApplyGoingContract) : this() {
        applyGoingContract = contract
    }

    constructor(contract: ApplyGoingContract.ApplyGoingLogContract, title: String): this(){
        applyGoingLogContract = contract
        logTitle.value = title

        when(logTitle.value){
            "토요외출" -> applyGoingLogContract?.setApplyList(saturdayItemList)
            "일요외출" -> applyGoingLogContract?.setApplyList(sundayItemList)
            "평일외출" -> applyGoingLogContract?.setApplyList(workdayItemList)
        }
    }

    constructor(contract: ApplyGoingContract.ApplyGoingDocContract, currentItem: Int): this(){
        applyGoingDocContract = contract
        nowCurrentItem.value = currentItem
    }

    fun settingViewPager()
        = applyGoingContract?.setViewPager(saturdayItemList.size, sundayItemList.size, workdayItemList.size)

    fun applyGoingClickDoc() = applyGoingContract?.intentApplyGoingDoc()

    fun applyGoingDocClickApply(){
        if(applyGoingGoDate.value.isNullOrBlank()){
            applyGoingDocContract?.setErrorApplyGoingGoDate()
        }
        else if(applyGoingGoTime.value.isNullOrBlank()){
            applyGoingDocContract?.setErrorApplyGoingGoTime()
        }
        else if(applyGoingReturnDate.value.isNullOrBlank()){
            applyGoingDocContract?.setErrorApplyGoingBackDate()
        }
        else if(applyGoingReturnTime.value.isNullOrBlank()){
            applyGoingDocContract?.setErrorApplyGoingBackTime()
        }
        else if (applyGoingReason.value.isNullOrBlank()){
            applyGoingDocContract?.setErrorApplyGoingReason()
        }
        // 동행인 없어도 됨 나중에 서버에 넘겨줄 때 없이 넘겨주면 됨
        else {
            // 서버 통신 필요
            when(nowCurrentItem.value){
                0 -> {
                    if (saturdayItemList.size != 5){
                        saturdayItemList.add(
                            ApplyGoingLogItemModel(
                                "${applyGoingGoDate.value} ${applyGoingGoTime.value} ~ ${applyGoingReturnTime.value}", "${applyGoingReason.value}"))
                    } else {
                        applyGoingDocContract?.createListFullWarningToast()
                    }
                }
                1 -> {
                    if (sundayItemList.size != 5){
                        sundayItemList.add(
                            ApplyGoingLogItemModel(
                                "${applyGoingGoDate.value} ${applyGoingGoTime.value} ~ ${applyGoingReturnTime.value}", "${applyGoingReason.value}"))
                    } else {
                        applyGoingDocContract?.createListFullWarningToast()
                    }
                }
                2 -> {
                    if (workdayItemList.size != 5){
                        workdayItemList.add(
                            ApplyGoingLogItemModel(
                                "${applyGoingGoDate.value} ${applyGoingGoTime.value} ~ ${applyGoingReturnTime.value}", "${applyGoingReason.value}"))
                    } else {
                        applyGoingDocContract?.createListFullWarningToast()
                    }
                }
            }
            applyGoingDocContract?.backApplyGoing()
        }
    }

    fun applyGoingDocClickBack() {
        applyGoingDocContract?.backApplyGoing()
    }

    fun applyGoingLogClickBack() {
        applyGoingLogContract?.backApplyGoing()
    }

    fun applyGoingClickDelete() {
        // 서버 통신 필요
        when(logTitle.value){
            "토요외출" -> saturdayItemList.removeAll(deleteData)
            "일요외출" -> sundayItemList.removeAll(deleteData)
            "평일외출" -> workdayItemList.removeAll(deleteData)
        }
        applyGoingLogContract?.backApplyGoing()
    }
}
