package dsm.android.v3.ui.applyExtensionStudy

import android.arch.lifecycle.MutableLiveData
import android.view.View
import android.widget.TextView

interface ApplyExtensionStudyContract{
    val selectSeatIndex: Int?

    fun drawMap(mapData: ArrayList<ArrayList<Any>>)
    fun changeTextViewColor(view: TextView)
    fun originTextViewColor(view: TextView)
    fun createShortToast(text: String)
    fun backApplyMenu()
}