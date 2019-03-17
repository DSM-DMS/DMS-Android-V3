package dsm.android.v3.ui.applyGoingEdit

interface ApplyGoingEditContract {
    fun createShortToast(text: String)
    fun setErrorApplyGoingGoDate()
    fun setErrorApplyGoingGoTime()
    fun setErrorApplyGoingReason()
    fun backApplyGoing()
}