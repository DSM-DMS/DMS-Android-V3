package dsm.android.v3.ui.applyGoingOut.applyGoingEdit

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dsm.android.v3.R
import dsm.android.v3.databinding.FragmentApplyGoingEditBinding
import dsm.android.v3.util.DataBindingFragment
import org.jetbrains.anko.support.v4.toast

class ApplyGoingEditFragment : DataBindingFragment<FragmentApplyGoingEditBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_apply_going_edit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = ApplyGoingEditViewModelFactory(activity!!.application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(ApplyGoingEditViewModel::class.java)

        viewModel.createShortToastSingleLiveEvent.observe(this, Observer { toast(it!!) })

        viewModel.backApplyGoingSingleLiveEvent.observe(this, Observer { findNavController().popBackStack() })

        binding.applyGoingEditViewModel = viewModel
    }
}