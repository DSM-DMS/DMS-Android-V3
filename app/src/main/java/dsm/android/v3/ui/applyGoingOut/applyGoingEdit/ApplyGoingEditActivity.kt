package dsm.android.v3.ui.applyGoingOut.applyGoingEdit

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityApplyGoingEditBinding
import dsm.android.v3.util.DataBindingActivity
import org.jetbrains.anko.toast

class ApplyGoingEditActivity : DataBindingActivity<ActivityApplyGoingEditBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_apply_going_edit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = ApplyGoingEditViewModelFactory(application)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(ApplyGoingEditViewModel::class.java)

        viewModel.createShortToastSingleLiveEvent.observe(this, Observer { toast(it!!) })

        viewModel.backApplyGoingSingleLiveEvent.observe(this, Observer { finish() })

        binding.applyGoingEditViewModel = viewModel
    }
}