package dsm.android.v3.ui.applyGoing

import android.arch.lifecycle.*
import dsm.android.v3.model.ApplyGoingLogItemModel
import dsm.android.v3.ui.applyGoingLog.ApplyGoingLogData.deleteData
import dsm.android.v3.ui.applyGoingLog.ApplyGoingLogData.saturdayItemList
import dsm.android.v3.ui.applyGoingLog.ApplyGoingLogData.sundayItemList
import dsm.android.v3.ui.applyGoingLog.ApplyGoingLogData.workdayItemList
import dsm.android.v3.util.LifecycleCallback

class ApplyGoingViewModel(val contract: ApplyGoingContract): ViewModel(), LifecycleCallback{

    override fun apply(event: Lifecycle.Event) {
        when(event) {
            Lifecycle.Event.ON_START -> {
                deleteData.clear()
                settingViewPager()
            }
        }
    }

    fun settingViewPager()
        = contract.setViewPager(saturdayItemList.size, sundayItemList.size, workdayItemList.size)

    fun applyGoingClickDoc() = contract.intentApplyGoingDoc()

}
