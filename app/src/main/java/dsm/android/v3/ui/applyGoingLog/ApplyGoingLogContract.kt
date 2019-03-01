package dsm.android.v3.ui.applyGoingLog

import dsm.android.v3.ui.applyGoing.ApplyGoingDataModel

interface ApplyGoingLogContract{
    fun setApplyList(models: ArrayList<ApplyGoingDataModel.ApplyGoingDataModel>)
    fun backApplyGoing()
    fun createShortToast(text: String)

    interface ApplyGoingLogRv{
        fun logItemClickTrue(model: ApplyGoingDataModel.ApplyGoingDataModel)
        fun logItemClickFalse(model: ApplyGoingDataModel.ApplyGoingDataModel)
    }
}