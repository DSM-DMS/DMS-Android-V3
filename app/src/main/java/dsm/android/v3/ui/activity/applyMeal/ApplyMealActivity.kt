package dsm.android.v3.ui.activity.applyMeal

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityApplyMealBinding
import dsm.android.v3.presentation.model.ApplyMealPagerModel
import dsm.android.v3.presentation.viewModel.applyMeal.ApplyMealViewModel
import dsm.android.v3.presentation.viewModel.applyMeal.ApplyMealViewModelFactory
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_apply_meal.*
import org.jetbrains.anko.find
import javax.inject.Inject

class ApplyMealActivity : DataBindingActivity<ActivityApplyMealBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_apply_meal

    @Inject
    lateinit var factory: ApplyMealViewModelFactory

    private val grayBorder by lazy {
        resources.getDrawable(R.drawable.radius_circle_gray)
    }

    private val primaryBorder by lazy {
        resources.getDrawable(R.drawable.radius_circle_primary)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_meal)
        setSupportActionBar(apply_meal_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = "주말급식 신청"
        apply_meal_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val viewModel = ViewModelProviders.of(this,factory).get(ApplyMealViewModel::class.java)
        viewModel.status.observe(this,{
            if(it==0){
                apply_meal_btn.background = grayBorder
            }else{
                apply_meal_btn.background = primaryBorder
            }
        })

        setPager()
    }

    private fun setPager() {
        apply_meal_vp.adapter = ApplyMealAdapter(
            arrayListOf(
                ApplyMealPagerModel(
                    "신청대기중",
                    "아직 주말급식 신청을 하지 않은 상태입니다. 주말급식신청 기간내에 신청,미신청 둘중 하나를 선택해주세요."
                ),
                ApplyMealPagerModel(
                    "신청",
                    "주말급식을 신청합니다. 토요일은 2끼(아침,점심)가 제공되고, 일요일은 3끼(아침,점심,저녁)가 제공됩니다."
                ),
                ApplyMealPagerModel(
                    "미신청",
                    "주말급식을 신청하지 않습니다."
                )
            )
        )
        apply_meal_vp.offscreenPageLimit = 3
    }

    inner class ApplyMealAdapter(val models: ArrayList<ApplyMealPagerModel>) : PagerAdapter() {
        override fun getCount(): Int = models.size

        override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) = container.removeView(any as View)

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater = LayoutInflater.from(baseContext)
            val view = layoutInflater.inflate(R.layout.item_apply_meal, container, false)
            view.find<TextView>(R.id.item_meal_title_tv).text = models[position].title
            view.find<TextView>(R.id.item_meal_explanation_tv).text = models[position].explanation
            container.addView(view)
            return view
        }

    }


}