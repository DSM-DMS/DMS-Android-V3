package dsm.android.v3.ui.applyStaying

import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityApplyStayingBinding
import dsm.android.v3.model.ApplyStayingModel
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.item_apply_staying.view.*
import org.jetbrains.anko.find

class ApplyStayingActivity: DataBindingActivity<ActivityApplyStayingBinding>(), ApplyStayingContract{
    override val layoutId: Int
        get() = R.layout.activity_apply_staying

    override lateinit var viewGroup: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ApplyStayingViewModelFactory(this)
        binding.applyStayingViewModel = ViewModelProviders.of(this, factory).get(ApplyStayingViewModel::class.java)
        setPager()
    }

    override fun getCurrentItem(): Int = binding.applyStayingApplyListPager.currentItem

    @RequiresApi(Build.VERSION_CODES.M)
    override fun changeColor(view: View){
        view.item_applyStaying_card.setCardBackgroundColor(getColor(R.color.colorPrimary))
        view.item_applyStaying_title_tv.setTextColor(getColor(R.color.colorWhite))
        view.item_applyStaying_explanation_tv.setTextColor(getColor(R.color.colorWhite))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun originalColor(view: View){
        view.item_applyStaying_card.setCardBackgroundColor(getColor(R.color.colorWhite))
        view.item_applyStaying_title_tv.setTextColor(getColor(R.color.colorPrimary))
        view.item_applyStaying_explanation_tv.setTextColor(getColor(R.color.colorGray600))
    }

    fun setPager(){
        binding.applyStayingApplyListPager.adapter = ApplyPageAdapter(
            arrayListOf(
            ApplyStayingModel(getString(R.string.apply_staying_staying_title), getString(R.string.apply_staying_staying_explanation))
            , ApplyStayingModel(getString(R.string.apply_staying_friday_title), getString(R.string.apply_staying_friday_explanation))
            , ApplyStayingModel(getString(R.string.apply_staying_saturday_go_title), getString(R.string.apply_staying_saturday_go_explanation))
            , ApplyStayingModel(getString(R.string.apply_staying_saturday_back_title), getString(R.string.apply_staying_saturday_back_explanation))
            ))
    }

    inner class ApplyPageAdapter(val models: ArrayList<ApplyStayingModel>): PagerAdapter(){

        override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1

        override fun getCount(): Int = models.size

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) = container.removeView(any as View)

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater = LayoutInflater.from(baseContext)
            val view = layoutInflater.inflate(R.layout.item_apply_staying, container, false)
            view.find<TextView>(R.id.item_applyStaying_title_tv).text = models[position].week
            view.find<TextView>(R.id.item_applyStaying_explanation_tv).text = models[position].description
            container.addView(view)
            return view
        }

        override fun startUpdate(container: ViewGroup) {
            super.startUpdate(container)
            viewGroup = container
        }

    }
}