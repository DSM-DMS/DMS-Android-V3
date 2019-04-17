package dsm.android.v3.ui.applyGoingOut.applyGoing

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.getDrawable
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.Api17CardView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavArgument
import androidx.navigation.fragment.findNavController
import dsm.android.v3.R
import dsm.android.v3.databinding.FragmentApplyGoingBinding
import dsm.android.v3.model.ApplyPagerModel
import dsm.android.v3.ui.applyGoingOut.ActionBarParcel
import dsm.android.v3.ui.applyGoingOut.applyGoingLog.ApplyGoingLogData
import dsm.android.v3.util.DataBindingFragment
import kotlinx.android.synthetic.main.activity_apply_going.*
import kotlinx.android.synthetic.main.item_apply_pager.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.textColor

class ApplyGoingFragment : DataBindingFragment<FragmentApplyGoingBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_apply_going

    override fun onStart() {
        super.onStart()
        val controller = findNavController()
        val actionBarParcel = controller.graph.arguments["actionBar"]!!.defaultValue as ActionBarParcel

        actionBarParcel.actionBar.title = "외출 신청"
        actionBarParcel.actionBar.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        viewModel.intentApplyGoingDocSingleLiveEvent.observe(this, Observer {
            val controller = findNavController()
            val actionBarParcel = controller.graph.arguments["actionBar"]!!.defaultValue as ActionBarParcel

            controller.navigate(ApplyGoingFragmentDirections.actionApplyGoingFragmentToApplyGoingDocFragment())
            actionBarParcel.actionBar.hide()
        })

        binding.applyGoingViewModel = viewModel
        register(binding.applyGoingViewModel!!)

    }

    inner class ApplyPageAdapter(private val models: ArrayList<ApplyPagerModel>) : PagerAdapter() {

        override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1

        override fun getCount(): Int = models.size

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) = container.removeView(any as View)

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val layoutInflater = LayoutInflater.from(context)
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
            val controller = findNavController()
            when (position) {
                0 -> controller.graph.addArgument("title", NavArgument.Builder().setDefaultValue("토요외출").build())
                1 -> controller.graph.addArgument("title", NavArgument.Builder().setDefaultValue("일요외출").build())
                2 -> controller.graph.addArgument("title", NavArgument.Builder().setDefaultValue("평일외출").build())
            }
            controller.navigate(ApplyGoingFragmentDirections.actionApplyGoingFragmentToApplyGoingLogFragment())
        }

        private fun changeColor(view: View) {
            view.item_applyGoing_card.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!,
                    R.color.colorPrimary
                )
            )
            view.item_applyGoing_title_tv.textColor = ContextCompat.getColor(context!!, R.color.colorWhite)
            view.item_applyGoing_explanation_tv.textColor =
                ContextCompat.getColor(context!!, R.color.colorWhite)
            view.item_applyGoing_count_tv.background = getDrawable(context!!, R.drawable.radius_circle_white)
            view.item_applyGoing_count_tv.textColor = ContextCompat.getColor(context!!, R.color.colorPrimary)
        }

        fun originalColor(view: View) {
            view.item_applyGoing_card.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!,
                    R.color.colorWhite
                )
            )
            view.item_applyGoing_title_tv.textColor = ContextCompat.getColor(context!!, R.color.colorPrimary)
            view.item_applyGoing_explanation_tv.textColor =
                ContextCompat.getColor(context!!, R.color.colorGray600)
            view.item_applyGoing_count_tv.background = getDrawable(context!!, R.drawable.radius_circle_primary)
            view.item_applyGoing_count_tv.textColor = ContextCompat.getColor(context!!, R.color.colorWhite)
        }
    }
}