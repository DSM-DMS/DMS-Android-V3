package dsm.android.v3.ui.applyGoingOut.applyGoing

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import androidx.navigation.fragment.findNavController
import dsm.android.v3.R
import dsm.android.v3.databinding.FragmentApplyGoingBinding
import dsm.android.v3.ui.applyGoingOut.ActionBarParcel
import dsm.android.v3.util.DataBindingFragment
import kotlinx.android.synthetic.main.item_apply_pager.view.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.textColor

class ApplyGoingFragment : DataBindingFragment<FragmentApplyGoingBinding>() {

    private val actionBarParcel by lazy { arguments?.getParcelable<ActionBarParcel>("actionBar") }
    private val actionBar by lazy { actionBarParcel!!.actionBar }

    override val layoutId: Int
        get() = R.layout.fragment_apply_going

    override fun onStart() {
        super.onStart()

        actionBar!!.title = "외출 신청"
        actionBar!!.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(ApplyGoingViewModel::class.java)

        viewModel.intentApplyGoingLogSingleLiveEvent.observe(this, Observer {
            changeColor(it!!)
            intentApplyGoingLog(it.tag as Int)
        })

        viewModel.createShortToastSingleLiveEvent.observe(this, Observer { toast(it!!) })

        viewModel.intentApplyGoingDocSingleLiveEvent.observe(this, Observer {
            findNavController().navigate(ApplyGoingFragmentDirections.actionApplyGoingFragmentToApplyGoingDocFragment())
            actionBarParcel!!.actionBar!!.hide()
        })

        binding.applyGoingViewModel = viewModel
        binding.applyGoingApplyListPager.adapter = ApplyPageAdapter(viewModel)
        register(binding.applyGoingViewModel!!)
    }

    private fun intentApplyGoingLog(position: Int) {
        val directions = ApplyGoingFragmentDirections.actionApplyGoingFragmentToApplyGoingLogFragment()
        directions.actionBar = actionBarParcel
        directions.goingOut = when (position) {
            0 -> "토요외출"
            1 -> "일요외출"
            2 -> "평일외출"
            else -> ""
        }
        findNavController().navigate(directions)
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
        view.item_applyGoing_count_tv.background =
            ContextCompat.getDrawable(context!!, R.drawable.radius_circle_white)
        view.item_applyGoing_count_tv.textColor = ContextCompat.getColor(context!!, R.color.colorPrimary)
    }

    private fun originalColor(view: View) {
        view.item_applyGoing_card.setCardBackgroundColor(
            ContextCompat.getColor(
                context!!,
                R.color.colorWhite
            )
        )
        view.item_applyGoing_title_tv.textColor = ContextCompat.getColor(context!!, R.color.colorPrimary)
        view.item_applyGoing_explanation_tv.textColor =
            ContextCompat.getColor(context!!, R.color.colorGray600)
        view.item_applyGoing_count_tv.background =
            ContextCompat.getDrawable(context!!, R.drawable.radius_circle_primary)
        view.item_applyGoing_count_tv.textColor = ContextCompat.getColor(context!!, R.color.colorWhite)
    }
}