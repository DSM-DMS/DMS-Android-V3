package dsm.android.v3.ui.applyGoingOut.applyGoingDoc

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityApplyGoingDocBinding
import dsm.android.v3.util.DataBindingActivity
import org.jetbrains.anko.toast

class ApplyGoingDocActivity : DataBindingActivity<ActivityApplyGoingDocBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_apply_going_doc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = ApplyGoingDocViewModelFactory(application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(ApplyGoingDocViewModel::class.java)

        viewModel.backApplyGoingSingleLiveEvent.observe(this, Observer { finish() })

        viewModel.createShortToastSingleLiveEvent.observe(this, Observer { toast(it!!) })

        binding.applyGoingDocViewModel = viewModel
    }
}