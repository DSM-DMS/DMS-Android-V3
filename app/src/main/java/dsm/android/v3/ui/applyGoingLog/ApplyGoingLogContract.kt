package dsm.android.v3.ui.applyGoingLog

import dsm.android.v3.model.ApplyGoingModel

interface ApplyGoingLogContract{
    fun setApplyList(models: ArrayList<ApplyGoingModel.ApplyGoingDataModel>)
    fun backApplyGoing()

    interface ApplyGoingLogRv{
        fun logItemClick(model: ApplyGoingModel.ApplyGoingDataModel)
    }
}