package dsm.android.v3.ui.applyMusic

import android.view.View
import android.view.ViewGroup

interface ApplyMusicContract {
    var viewGroup: ViewGroup

    fun closeApplyMusic()
    fun setViewPager(mondayCount: Int, tuesdayCount: Int, wednesdayCount: Int, thursdayCount: Int, fridayCount: Int, saturdayCount: Int, sunday: Int)
    fun originalColor(view: View)
    fun changeColor(view: View)
}