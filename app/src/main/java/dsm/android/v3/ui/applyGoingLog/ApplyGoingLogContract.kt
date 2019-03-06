package dsm.android.v3.ui.applyGoingLog

import dsm.android.v3.model.ApplyGoingModel

interface ApplyGoingLogContract{
    fun setApplyList(models: ArrayList<ApplyGoingModel.ApplyGoingDataModel>)
    fun backApplyGoing()
    fun createShortToast(text: String)

    interface ApplyGoingLogRv{
        fun logItemClickTrue(model: ApplyGoingModel.ApplyGoingDataModel)
        fun logItemClickFalse(model: ApplyGoingModel.ApplyGoingDataModel)
    }
}