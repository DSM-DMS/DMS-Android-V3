package dsm.android.v3.ui.applyGoingEdit

interface ApplyGoingEditContract {
    fun createShortToast(text: String)
    fun setErrorApplyGoingGoDate(text: String)
    fun setErrorApplyGoingGoTime(text: String)
    fun setErrorApplyGoingReason(text: String)
    fun backApplyGoing()
}