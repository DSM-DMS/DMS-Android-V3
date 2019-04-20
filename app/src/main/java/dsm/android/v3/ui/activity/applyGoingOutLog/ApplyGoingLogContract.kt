package dsm.android.v3.ui.activity.applyGoingOutLog

import dsm.android.v3.data.entity.ApplyGoingOutModel

interface ApplyGoingLogContract{
    fun setApplyList(models: ArrayList<ApplyGoingOutModel.ApplyGoingDataModel>)

    interface ApplyGoingLogRv{
        fun logItemClick(model: ApplyGoingOutModel.ApplyGoingDataModel)
    }
}