package dsm.android.v3.ui.applyGoing

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
import dsm.android.v3.ui.applyGoingDoc.ApplyGoingDocActivity
import dsm.android.v3.ui.applyGoingLog.ApplyGoingLogActivity
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_apply_going.*
import kotlinx.android.synthetic.main.item_apply_pager.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast


class ApplyGoingActivity : DataBindingActivity<ActivityApplyGoingBinding>(),  ApplyGoingContract {

    override val layoutId: Int
        get() = R.layout.activity_apply_going

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(applyGoing_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        title = "외출 신청"
        applyGoing_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val factory = ApplyGoingViewModelFactory(this)
        binding.applyGoingViewModel = ViewModelProviders.of(this, factory).get(ApplyGoingViewModel::class.java)
        register(binding.applyGoingViewModel!!)
    }

    override fun createShortToast(text: String) = toast(text).show()

    override fun setViewPager(saturdayCount: Int, sundayCount: Int, workdayCount: Int){
        val models = arrayListOf(
            ApplyPagerModel(getString(R.string.apply_going_saturday_title), getString(R.string.apply_going_saturday_explanation), saturdayCount),
            ApplyPagerModel(getString(R.string.apply_going_sunday_title), getString(R.string.apply_going_sunday_explanation), sundayCount),
            ApplyPagerModel(getString(R.string.apply_going_workday_title), getString(R.string.apply_going_workday_explanation),workdayCount)
        )
        applyGoing_apply_list_pager.adapter = ApplyPageAdapter(models)
    }

    override fun intentApplyGoingDoc() = startActivity<ApplyGoingDocActivity>()

    inner class ApplyPageAdapter(val models: ArrayList<ApplyPagerModel>) : PagerAdapter() {

        override fun isViewFromObject(p0: View, p1: Any): Boolean  = p0 == p1

        override fun getCount(): Int = models.size

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) = container.removeView(any as View)

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater = LayoutInflater.from(baseContext)
            val view = layoutInflater.inflate(R.layout.item_apply_pager, container, false)
            view.find<TextView>(R.id.item_applyGoing_title_tv).text = models[position].week
            view.find<TextView>(R.id.item_applyGoing_explanation_tv).text = models[position].description
            view.find<TextView>(R.id.item_applyGoing_count_tv).text = models[position].cnt.toString()
            view.find<Api17CardView>(R.id.item_applyGoing_card).setOnTouchListener { v, event ->
                when(event.action){
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

        fun intentApplyGoingLog(position: Int) {
            when(position){
                0 -> startActivity<ApplyGoingLogActivity>("title" to "토요외출")
                1 -> startActivity<ApplyGoingLogActivity>("title" to "일요외출")
                2 -> startActivity<ApplyGoingLogActivity>("title" to "평일외출")
            }
        }

        fun changeColor(view: View){
            view .item_applyGoing_card.setCardBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
            view.item_applyGoing_title_tv.textColor = ContextCompat.getColor(applicationContext, R.color.colorWhite)
            view.item_applyGoing_explanation_tv.textColor = ContextCompat.getColor(applicationContext, R.color.colorWhite)
            view.item_applyGoing_count_tv.background = getDrawable(R.drawable.radius_circle_white)
            view.item_applyGoing_count_tv.textColor = ContextCompat.getColor(applicationContext, R.color.colorPrimary)
        }

        fun originalColor(view: View){
            view.item_applyGoing_card.setCardBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorWhite))
            view.item_applyGoing_title_tv.textColor = ContextCompat.getColor(applicationContext, R.color.colorPrimary)
            view.item_applyGoing_explanation_tv.textColor = ContextCompat.getColor(applicationContext,R.color.colorGray600)
            view.item_applyGoing_count_tv.background = getDrawable(R.drawable.radius_circle_primary)
            view.item_applyGoing_count_tv.textColor = ContextCompat.getColor(applicationContext, R.color.colorWhite)
        }
    }
}