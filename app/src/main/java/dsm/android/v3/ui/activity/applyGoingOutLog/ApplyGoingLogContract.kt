package dsm.android.v3.ui.activity.applyGoingOutLog

import dsm.android.v3.data.entity.ApplyGoingOut

interface ApplyGoingLogContract{
    fun setApplyList(models: ArrayList<ApplyGoingOut.ApplyGoingDataModel>)

    interface ApplyGoingLogRv{
        fun logItemClick(model: ApplyGoingOut.ApplyGoingDataModel)
    }
}