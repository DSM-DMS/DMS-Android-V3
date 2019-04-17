package dsm.android.v3.ui.applyGoingOut.applyGoingLog

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import androidx.navigation.fragment.findNavController
import dsm.android.v3.R
import dsm.android.v3.adapter.ApplyGoingLogAdapter
import dsm.android.v3.databinding.FragmentApplyGoingLogBinding
import dsm.android.v3.ui.applyGoingOut.ActionBarParcel
import dsm.android.v3.util.DataBindingFragment

class ApplyGoingLogFragment : DataBindingFragment<FragmentApplyGoingLogBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_apply_going_log

    override fun onStart() {
        super.onStart()

        val actionBarParcel = findNavController().graph.arguments["actionBar"]!!.defaultValue as ActionBarParcel
        actionBarParcel.actionBar.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = findNavController().graph.arguments["title"]!!.defaultValue as String
        val actionBarParcel = findNavController().graph.arguments["actionBar"]!!.defaultValue as ActionBarParcel

        actionBarParcel.actionBar.title = title

        val viewModelFactory = ApplyGoingLogViewModelFactory(title)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(ApplyGoingLogViewModel::class.java)

        viewModel.logItemClickSingleLiveEvent.observe(this, Observer {
            findNavController().navigate(ApplyGoingLogFragmentDirections.actionApplyGoingLogFragmentToApplyGoingEditFragment())
            actionBarParcel.actionBar.hide()
        })

        binding.applyGoingApplyRecordRv.layoutManager = LinearLayoutManager(context)
        binding.applyGoingApplyRecordRv.adapter = ApplyGoingLogAdapter(viewModel)
        binding.applyGoingLogViewModel = viewModel
    }
}