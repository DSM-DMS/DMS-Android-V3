package dsm.android.v3.ui.applyGoingOut.applyGoing

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.Api17CardView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityApplyGoingBinding
import dsm.android.v3.model.ApplyPagerModel
import dsm.android.v3.ui.applyGoingOut.applyGoingDoc.ApplyGoingDocActivity
import dsm.android.v3.ui.applyGoingOut.applyGoingLog.ApplyGoingLogActivity
import dsm.android.v3.ui.applyGoingOut.applyGoingLog.ApplyGoingLogData
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_apply_going.*
import kotlinx.android.synthetic.main.item_apply_pager.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast

class ApplyGoingActivity : DataBindingActivity<ActivityApplyGoingBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_apply_going

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(applyGoing_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = "외출 신청"
        applyGoing_toolbar.setNavigationOnClickListener { onBackPressed() }

        val viewModel = ViewModelProviders.of(this).get(ApplyGoingViewModel::class.java)

        viewModel.setViewPagerSingleLiveEvent.observe(this, Observer {
            val models = arrayListOf(
                ApplyPagerModel(
                    getString(R.string.apply_going_saturday_title),
                    getString(R.string.apply_going_saturday_explanation),
                    ApplyGoingLogData.saturdayItemList.size
                ),
                ApplyPagerModel(
                    getString(R.string.apply_going_sunday_title),
                    getString(R.string.apply_going_sunday_explanation),
                    ApplyGoingLogData.sundayItemList.size
                ),
                ApplyPagerModel(
                    getString(R.string.apply_going_workday_title),
                    getString(R.string.apply_going_workday_explanation),
                    ApplyGoingLogData.workdayItemList.size
                )
            )
            applyGoing_apply_list_pager.adapter = ApplyPageAdapter(models)
        })

        viewModel.createShortToastSingleLiveEvent.observe(this, Observer { toast(it!!) })

        viewModel.intentApplyGoingDocSingleLiveEvent.observe(this, Observer { startActivity<ApplyGoingDocActivity>() })

        binding.applyGoingViewModel = viewModel
        register(binding.applyGoingViewModel!!)
    }

    inner class ApplyPageAdapter(private val models: ArrayList<ApplyPagerModel>) : PagerAdapter() {

        override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1

        override fun getCount(): Int = models.size

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) = container.removeView(any as View)

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater = LayoutInflater.from(baseContext)
            val view = layoutInflater.inflate(R.layout.item_apply_pager, container, false)
            view.find<TextView>(R.id.item_applyGoing_title_tv).text = models[position].week
            view.find<TextView>(R.id.item_applyGoing_explanation_tv).text = models[position].description
            view.find<TextView>(R.id.item_applyGoing_count_tv).text = models[position].cnt.toString()
            view.find<Api17CardView>(R.id.item_applyGoing_card).setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_UP -> {
                        changeColor(view)
                        intentApplyGoingLog(position)
                    }
                }
                true
            }
            container.addView(view)
            return view
        }

        private fun intentApplyGoingLog(position: Int) {
            when (position) {
                0 -> startActivity<ApplyGoingLogActivity>("title" to "토요외출")
                1 -> startActivity<ApplyGoingLogActivity>("title" to "일요외출")
                2 -> startActivity<ApplyGoingLogActivity>("title" to "평일외출")
            }
        }

        private fun changeColor(view: View) {
            view.item_applyGoing_card.setCardBackgroundColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.colorPrimary
                )
            )
            view.item_applyGoing_title_tv.textColor = ContextCompat.getColor(applicationContext, R.color.colorWhite)
            view.item_applyGoing_explanation_tv.textColor =
                ContextCompat.getColor(applicationContext, R.color.colorWhite)
            view.item_applyGoing_count_tv.background = getDrawable(R.drawable.radius_circle_white)
            view.item_applyGoing_count_tv.textColor = ContextCompat.getColor(applicationContext, R.color.colorPrimary)
        }

        fun originalColor(view: View) {
            view.item_applyGoing_card.setCardBackgroundColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.colorWhite
                )
            )
            view.item_applyGoing_title_tv.textColor = ContextCompat.getColor(applicationContext, R.color.colorPrimary)
            view.item_applyGoing_explanation_tv.textColor =
                ContextCompat.getColor(applicationContext, R.color.colorGray600)
            view.item_applyGoing_count_tv.background = getDrawable(R.drawable.radius_circle_primary)
            view.item_applyGoing_count_tv.textColor = ContextCompat.getColor(applicationContext, R.color.colorWhite)
        }
    }
}