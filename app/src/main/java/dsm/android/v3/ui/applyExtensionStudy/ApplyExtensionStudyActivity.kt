package dsm.android.v3.ui.applyExtensionStudy

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Api17CardView
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityApplyExtensionStudyBinding
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_apply_extension_study.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ApplyExtensionStudyActivity: DataBindingActivity<ActivityApplyExtensionStudyBinding>(), ApplyExtensionStudyContract{

    override val layoutId: Int
        get() = R.layout.activity_apply_extension_study

    override var selectSeatIndex: Int? = null
    var clickedSeat: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ApplyExtensionStudyViewModelFactory(this)
        binding.applyExtensionStudyViewModel = ViewModelProviders.of(this, factory).get(ApplyExtensionStudyViewModel::class.java)
        register(binding.applyExtensionStudyViewModel!!)
    }

    override fun createShortToast(text: String) = toast(text).show()

    override fun backApplyMenu() = finish()

    override fun changeTextViewColor(view: TextView){
        view.backgroundResource = R.drawable.radius_primaryline_view
        view.textColor = ContextCompat.getColor(applicationContext, R.color.colorPrimary)
    }
    override fun originTextViewColor(view: TextView){
        view.backgroundResource = R.drawable.radius_grayline_view
        view.textColor = ContextCompat.getColor(applicationContext, R.color.colorGray400)
    }

    override fun drawMap(map: ArrayList<ArrayList<Any>>) {
        selectSeatIndex = 0
        applyExtension_map_lay.removeAllViews()

        val seatHorizonMargin = dip(8)
        val seatVerticalMargin = dip(16)
        val seatSize = dip(36)
        val layoutParam = LinearLayout.LayoutParams(seatSize, seatSize)
        layoutParam.verticalMargin = seatVerticalMargin
        layoutParam.horizontalMargin = seatHorizonMargin

        for (horizonMap in map){
            applyExtension_map_lay.addView(
                UI {
                    linearLayout {
                        for (seat in horizonMap) {
                            when(seat) {
                                is Double -> {
                                    if (seat > 0) {
                                        textView {
                                            layoutParams = layoutParam
                                            setTextView(this, seat)
                                            onClick {
                                                if (clickedSeat != this@textView){
                                                    clickedSeat?.let { clickedSeat!!.backgroundResource = R.drawable.radius_circle_gray }
                                                    this@textView.backgroundResource = R.drawable.radius_circle_primary
                                                    clickedSeat = this@textView
                                                    selectSeatIndex = seat.toInt()
                                                }
                                            }
                                        }
                                    } else {
                                        space { layoutParams = layoutParam }
                                    }
                                }
                                is String -> {
                                    textView(seat) {
                                        gravity = Gravity.CENTER
                                        layoutParams = layoutParam
                                        setTextView(this, seat)
                                    }
                                }
                                else -> view { layoutParams = layoutParam }
                            }
                        }
                    }
                }.view)
        }
    }

    private fun setTextView(textView: TextView, text: Any){
        when(text){
            is Double -> textView.backgroundResource = R.drawable.radius_circle_gray
            is String -> textView.backgroundResource = R.drawable.radius_circle_primary
        }
        textView.gravity = Gravity.CENTER
        textView.textSizeDimen = R.dimen.typo_body2
        textView.textColor = ContextCompat.getColor(this, R.color.colorWhite)
    }
}