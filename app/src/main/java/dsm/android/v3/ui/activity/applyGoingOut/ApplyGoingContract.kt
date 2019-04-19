package dsm.android.v3.ui.activity.applyGoingOut

interface ApplyGoingContract{
    fun setViewPager(saturdayCount: Int, sundayCount: Int, workdayCount: Int)
    fun intentApplyGoingDoc()
    fun createShortToast(text: String)
}