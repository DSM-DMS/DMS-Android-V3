package dsm.android.v3.ui.applyGoingOut.applyGoingDoc

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dsm.android.v3.R
import dsm.android.v3.databinding.FragmentApplyGoingDocBinding
import dsm.android.v3.util.DataBindingFragment
import org.jetbrains.anko.support.v4.toast

class ApplyGoingDocFragment : DataBindingFragment<FragmentApplyGoingDocBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_apply_going_doc

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(ApplyGoingDocViewModel::class.java)

        viewModel.backApplyGoingSingleLiveEvent.observe(this, Observer { findNavController().popBackStack() })

        viewModel.createShortToastSingleLiveEvent.observe(this, Observer { toast(it!!) })

        binding.applyGoingDocViewModel = viewModel
    }
}