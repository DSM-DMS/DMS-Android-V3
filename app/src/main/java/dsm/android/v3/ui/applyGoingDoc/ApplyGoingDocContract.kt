package dsm.android.v3.ui.applyGoingDoc

interface ApplyGoingDocContract{
    fun backApplyGoing()
    fun setErrorApplyGoingGoDate()
    fun setErrorApplyGoingGoTime()
    fun setErrorApplyGoingBackDate()
    fun setErrorApplyGoingBackTime()
    fun setErrorApplyGoingReason()
    fun createShortToast(text: String)
}