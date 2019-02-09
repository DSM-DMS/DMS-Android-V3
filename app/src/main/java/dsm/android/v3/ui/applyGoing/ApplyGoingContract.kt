package dsm.android.v3.ui.applyGoing

import dsm.android.v3.model.ApplyGoingLogItemModel

interface ApplyGoingContract{
    fun setViewPager(saturdayCount: Int, sundayCount: Int, workdayCount: Int)
    fun intentApplyGoingDoc()

    interface ApplyGoingLogContract{
        fun setApplyList(models: ArrayList<ApplyGoingLogItemModel>)
        fun backApplyGoing()
    }

    interface ApplyGoingDocContract{
        fun backApplyGoing()
        fun setErrorApplyGoingGoDate()
        fun setErrorApplyGoingGoTime()
        fun setErrorApplyGoingBackDate()
        fun setErrorApplyGoingBackTime()
        fun setErrorApplyGoingReason()
        fun createListFullWarningToast()
    }

    interface ApplyGoingLogRv{
        fun logItemClickTrue(model: ApplyGoingLogItemModel)
        fun logItemClickFalse(model: ApplyGoingLogItemModel)
    }
}