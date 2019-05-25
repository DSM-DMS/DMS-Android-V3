package dsm.android.v3.ui.fragment.applyGoingOutEdit

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dsm.android.v3.R
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.databinding.FragmentApplyGoingEditBinding
import dsm.android.v3.domain.repository.applyGoingOut.ApplyGoingOutRepositoryImpl
import dsm.android.v3.presentation.di.app.BaseApp
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.viewModel.applyGoingOut.ApplyGoingViewModelFactory
import dsm.android.v3.presentation.viewModel.applyGoingOutEdit.ApplyGoingEditViewModel
import dsm.android.v3.util.DataBindingFragment
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

@ActivityScope
class ApplyGoingEditFragment : DataBindingFragment<FragmentApplyGoingEditBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_apply_going_edit

    @Inject
    lateinit var factory: ApplyGoingViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(this, factory).get(ApplyGoingEditViewModel::class.java)

        viewModel.createShortToastSingleLiveEvent.observe(this, Observer { toast(it!!) })

        viewModel.backApplyGoingSingleLiveEvent.observe(this, Observer { findNavController().popBackStack() })

        binding.applyGoingEditViewModel = viewModel
    }
}