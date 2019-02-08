package dsm.android.v3.ui.applyGoing

import android.arch.lifecycle.*
import android.util.Log
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

    val logTitle = MutableLiveData<String>()

    val applyGoingGoDate = MutableLiveData<String>()
    val applyGoingGoTime = MutableLiveData<String>()
    val applyGoingBackDate = MutableLiveData<String>()
    val applyGoingBackTime = MutableLiveData<String>()
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

    constructor(contract: ApplyGoingContract.ApplyGoingDocContract): this(){
        applyGoingDocContract = contract
    }

    fun settingViewPager()
        = applyGoingContract?.setViewPager(saturdayItemList.size, sundayItemList.size, workdayItemList.size)

    fun applyGoingClickDoc() = applyGoingContract?.intentApplyGoingDoc()

    fun applyGoingDocClickApply(){
        

        // TODO("신청한 거 추가되게 해야 함")
        applyGoingDocContract?.backApplyGoing()
    }

    fun applyGoingDocClickBack() {
        applyGoingDocContract?.backApplyGoing()
    }

    fun applyGoingLogClickBack() {
        applyGoingLogContract?.backApplyGoing()
    }

    fun applyGoingClickDelete() {
        Log.e("지워지는 데이터", "$deleteData")
        when(logTitle.value){
            "토요외출" -> saturdayItemList.removeAll(deleteData)
            "일요외출" -> sundayItemList.removeAll(deleteData)
            "평일외출" -> workdayItemList.removeAll(deleteData)
        }
        applyGoingLogContract?.backApplyGoing()
    }
}
