package dsm.android.v3.ui.applyStaying

import android.view.View
import android.view.ViewGroup

interface  ApplyStayingContract {

    var viewGroup: ViewGroup

    fun closeApplyStaying()
    fun changeColor(view: View)
    fun originalColor(view: View)
    fun createShortToast(text: String)
}