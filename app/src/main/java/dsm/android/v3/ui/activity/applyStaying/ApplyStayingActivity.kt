package dsm.android.v3.ui.activity.applyStaying

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityApplyStayingBinding
import dsm.android.v3.presentation.model.ApplyStayingPagerModel
import dsm.android.v3.presentation.viewModel.applyStaying.ApplyStayingViewModel
import dsm.android.v3.presentation.viewModel.applyStaying.ApplyStayingViewModelFactory
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_apply_staying.*
import kotlinx.android.synthetic.main.item_apply_staying.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast
import javax.inject.Inject

class ApplyStayingActivity: DataBindingActivity<ActivityApplyStayingBinding>(){

    override val layoutId: Int
        get() = R.layout.activity_apply_staying

    @Inject
    lateinit var factory: ApplyStayingViewModelFactory

    lateinit var viewGroup: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(applyStaying_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "잔류 신청"
        applyStaying_toolbar.setNavigationOnClickListener { onBackPressed() }

        val viewModel = ViewModelProviders.of(this, factory).get(ApplyStayingViewModel::class.java)
        viewModel.toastLiveData.observe(this, Observer { toast(it!!) })
        viewModel.changeColorLiveEvent.observe(this, Observer {
            val view = viewGroup.getChildAt(viewModel.pageStatusLiveData.value!!)
            changeColor(view)
            binding.applyStayingViewModel!!.selectedView.value = view
        })
        viewModel.originalColorLiveEvent.observe(this, Observer {
            originalColor(viewModel.selectedView.value!!)
        })

        binding.applyStayingViewModel = viewModel
        setPager()
    }

    fun changeColor(view: View){
        view.item_applyStaying_card.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
        view.item_applyStaying_title_tv.textColor = ContextCompat.getColor(this, R.color.colorWhite)
        view.item_applyStaying_explanation_tv.textColor = ContextCompat.getColor(this, R.color.colorWhite)
    }

    fun originalColor(view: View){
        view.item_applyStaying_card.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorWhiteToGray))
        view.item_applyStaying_title_tv.textColor = ContextCompat.getColor(this, R.color.colorPrimary)
        view.item_applyStaying_explanation_tv.textColor = ContextCompat.getColor(this, R.color.colorGray600)
    }

    fun setPager(){
        applyStaying_apply_list_pager.adapter = ApplyPageAdapter(
            arrayListOf(
                ApplyStayingPagerModel(
                    getString(R.string.apply_staying_friday_title),
                    getString(R.string.apply_staying_friday_explanation)
                )
            ,
                ApplyStayingPagerModel(
                    getString(R.string.apply_staying_saturday_go_title),
                    getString(R.string.apply_staying_saturday_go_explanation)
                )
            ,
                ApplyStayingPagerModel(
                    getString(R.string.apply_staying_saturday_back_title),
                    getString(R.string.apply_staying_saturday_back_explanation)
                )
            ,
                ApplyStayingPagerModel(
                    getString(R.string.apply_staying_staying_title),
                    getString(R.string.apply_staying_staying_explanation)
                )
            ))
        applyStaying_apply_list_pager.offscreenPageLimit = 3
    }

    inner class ApplyPageAdapter(val models: ArrayList<ApplyStayingPagerModel>): PagerAdapter(){

        override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1

        override fun getCount(): Int = models.size

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) = container.removeView(any as View)

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater = LayoutInflater.from(container.context)
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