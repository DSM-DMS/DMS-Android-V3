package dsm.android.v3.ui.applyGoing

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dsm.android.v3.R
import dsm.android.v3.databinding.FragmentApplyGoingSundayBinding
import dsm.android.v3.util.DataBindingFragment

class ApplyGoingSundayFragment: DataBindingFragment<FragmentApplyGoingSundayBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_apply_going_sunday


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.applyGoingViewModel = ViewModelProviders.of(this).get(ApplyGoingViewModel::class.java)
        return rootView
    }
}