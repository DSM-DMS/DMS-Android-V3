package dsm.android.v3.ui.fragment.applyGoingOutLog

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import androidx.navigation.fragment.findNavController
import dsm.android.v3.R
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.ui.adapter.ApplyGoingLogAdapter
import dsm.android.v3.databinding.FragmentApplyGoingLogBinding
import dsm.android.v3.domain.repository.applyGoingOut.ApplyGoingOutRepositoryImpl
import dsm.android.v3.presentation.viewModel.applyGoingOut.ApplyGoingViewModelFactory
import dsm.android.v3.presentation.viewModel.applyGoingOutLog.ApplyGoingLogViewModel
import dsm.android.v3.presentation.viewModel.applyGoingOutLog.ApplyGoingLogViewModelFactory
import dsm.android.v3.util.DataBindingFragment
import javax.inject.Inject

class ApplyGoingLogFragment : DataBindingFragment<FragmentApplyGoingLogBinding>() {

    private val actionBar by lazy { (activity as AppCompatActivity).supportActionBar }
    private val title by lazy { arguments!!.getString("goingOut") }

    override val layoutId: Int
        get() = R.layout.fragment_apply_going_log

    override fun onStart() {
        super.onStart()

        actionBar!!.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = ApplyGoingLogViewModelFactory(title!!)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(ApplyGoingLogViewModel::class.java)

        viewModel.logItemClickSingleLiveEvent.observe(this, Observer {
            findNavController().navigate(ApplyGoingLogFragmentDirections.actionApplyGoingLogFragmentToApplyGoingEditFragment())
            actionBar!!.hide()
        })

        binding.applyGoingApplyRecordRv.layoutManager = LinearLayoutManager(context)
        binding.applyGoingApplyRecordRv.adapter = ApplyGoingLogAdapter(viewModel)
        binding.applyGoingLogViewModel = viewModel
    }
}