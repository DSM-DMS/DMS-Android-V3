package dsm.android.v3.ui.applyGoingLog

import dsm.android.v3.model.ApplyGoingLogItemModel

interface ApplyGoingLogContract{
    fun setApplyList(models: ArrayList<ApplyGoingLogItemModel>)
    fun backApplyGoing()

    interface ApplyGoingLogRv{
        fun logItemClickTrue(model: ApplyGoingLogItemModel)
        fun logItemClickFalse(model: ApplyGoingLogItemModel)
    }
}