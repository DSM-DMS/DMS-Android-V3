package dsm.android.v3.ui.fragment.applyGoingOut

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import androidx.navigation.fragment.findNavController
import dsm.android.v3.R
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.databinding.FragmentApplyGoingBinding
import dsm.android.v3.domain.repository.applyGoingOut.ApplyGoingOutRepositoryImpl
import dsm.android.v3.presentation.di.app.BaseApp
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.viewModel.applyGoingOut.ApplyGoingViewModel
import dsm.android.v3.presentation.viewModel.applyGoingOut.ApplyGoingViewModelFactory
import dsm.android.v3.ui.adapter.ApplyPageAdapter
import dsm.android.v3.util.DataBindingFragment
import kotlinx.android.synthetic.main.item_apply_pager.view.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.textColor
import javax.inject.Inject

@ActivityScope
class ApplyGoingFragment : DataBindingFragment<FragmentApplyGoingBinding>() {

    private val actionBar by lazy { (activity as AppCompatActivity).supportActionBar }

    override val layoutId: Int
        get() = R.layout.fragment_apply_going

    override fun onStart() {
        super.onStart()

        actionBar?.show()
    }

    @Inject
    lateinit var apiClient: ApiClient

    val factory by lazy { ApplyGoingViewModelFactory(ApplyGoingOutRepositoryImpl(apiClient)) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(this, factory).get(ApplyGoingViewModel::class.java)

        viewModel.applyGoingLogSingleLiveEvent.observe(this, Observer {
            changeColor(it!!)
            intentApplyGoingLog(it.tag as Int)
        })

        viewModel.createShortToastSingleLiveEvent.observe(this, Observer { toast(it!!) })

        viewModel.applyGoingDocSingleLiveEvent.observe(this, Observer {
            findNavController().navigate(ApplyGoingFragmentDirections.actionApplyGoingFragmentToApplyGoingDocFragment())
            actionBar?.hide()
        })

        binding.applyGoingViewModel = viewModel
        binding.applyGoingApplyListPager.adapter = ApplyPageAdapter(viewModel)
        register(binding.applyGoingViewModel!!)
    }

    private fun intentApplyGoingLog(position: Int) {
        val directions =
            ApplyGoingFragmentDirections.actionApplyGoingFragmentToApplyGoingLogFragment()
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