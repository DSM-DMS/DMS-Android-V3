package dsm.android.v3.ui.activity.applyExtensionStudy

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.databinding.ActivityApplyExtensionStudyBinding
import dsm.android.v3.domain.repository.applyExtensionStudy.ApplyExtensionStudyRepositoryImpl
import dsm.android.v3.domain.repository.register.RegisterRepositoryImpl
import dsm.android.v3.presentation.di.app.BaseApp
import dsm.android.v3.presentation.viewModel.applyExtensionStudy.ApplyExtensionStudyViewModel
import dsm.android.v3.presentation.viewModel.applyExtensionStudy.ApplyExtensionStudyViewModelFactory
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_apply_extension_study.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import javax.inject.Inject

class ApplyExtensionStudyActivity: DataBindingActivity<ActivityApplyExtensionStudyBinding>(){

    @Inject
    lateinit var apiClient: ApiClient

    override val layoutId: Int
        get() = R.layout.activity_apply_extension_study

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ApplyExtensionStudyViewModelFactory(ApplyExtensionStudyRepositoryImpl(apiClient))
        val viewModel = ViewModelProviders.of(this, factory).get(ApplyExtensionStudyViewModel::class.java)

        viewModel.clickedClassView.value = applyExtension_gaonsil_tv
        changeTextViewColor(applyExtension_gaonsil_tv)
        viewModel.clickedTimeView.value = applyExtension_eleven_tv
        changeTextViewColor(applyExtension_eleven_tv)

        viewModel.backApplyMenuLiveEvent.observe(this, Observer { finish() })
        viewModel.toastLiveData.observe(this, Observer { toast(it!!) })
        viewModel.clickedClassView.observe(this, Observer { changeTextViewColor(it!! as TextView) })
        viewModel.clickedTimeView.observe(this, Observer { changeTextViewColor(it!! as TextView) })
        viewModel.originalColorLiveData.observe(this, Observer { originTextViewColor(it!!) })
        viewModel.drawMapLiveData.observe(this, Observer { drawMap(it!!) })

        binding.applyExtensionStudyViewModel = viewModel
        register(binding.applyExtensionStudyViewModel!!)
    }

    fun changeTextViewColor(view: TextView){
        view.backgroundResource = R.drawable.radius_primaryline_primary_view
        view.textColor = ContextCompat.getColor(applicationContext, R.color.colorPrimary)
    }

    fun originTextViewColor(view: TextView){
        view.backgroundResource = R.drawable.radius_grayline_view
        view.textColor = ContextCompat.getColor(applicationContext, R.color.colorGray400)
    }

    fun drawMap(map: ArrayList<ArrayList<Any>>) {
        binding.applyExtensionStudyViewModel!!.selectedSeatIndex.value = 0
        applyExtension_map_lay.removeAllViews()

        val seatHorizonMargin = dip(8)
        val seatVerticalMargin = dip(16)
        val seatSize = dip(40)
        val layoutParam = LinearLayout.LayoutParams(seatSize, seatSize)
        layoutParam.verticalMargin = seatVerticalMargin
        layoutParam.horizontalMargin = seatHorizonMargin

        var clickedSeat: TextView? = null

        for (horizonMap in map){
            applyExtension_map_lay.addView(
                UI {
                    linearLayout {
                        for (seat in horizonMap) {
                            when(seat) {
                                is Double -> {
                                    if (seat > 0) {
                                        textView {
                                            text = seat.toInt().toString()
                                            layoutParams = layoutParam
                                            setTextView(this, seat)
                                            onClick {
                                                if (clickedSeat != this@textView){
                                                    clickedSeat?.let { clickedSeat!!.backgroundResource = R.drawable.radius_circle_gray }
                                                    this@textView.backgroundResource = R.drawable.radius_primaryline_gray_view
                                                    this@textView.text = seat.toInt().toString()
                                                    clickedSeat = this@textView
                                                    binding.applyExtensionStudyViewModel!!.selectedSeatIndex.value = seat.toInt()
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