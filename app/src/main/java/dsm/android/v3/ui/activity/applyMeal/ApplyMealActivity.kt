package dsm.android.v3.ui.activity.applyMeal

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dagger.android.support.DaggerAppCompatActivity
import dsm.android.v3.R
import dsm.android.v3.presentation.model.ApplyMealPagerModel
import kotlinx.android.synthetic.main.activity_apply_meal.*
import kotlinx.android.synthetic.main.activity_apply_music_dom.*
import org.jetbrains.anko.find

class ApplyMealActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_meal)
        setSupportActionBar(apply_meal_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        title = "주말급식 신청"
        apply_meal_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

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