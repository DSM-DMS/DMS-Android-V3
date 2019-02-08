package dsm.android.v3.ui.applyGoing

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityApplyGoingDocBinding
import dsm.android.v3.util.DataBindingActivity
import org.jetbrains.anko.startActivity

class ApplyGoingDocActivity: DataBindingActivity<ActivityApplyGoingDocBinding>(), ApplyGoingContract.ApplyGoingDocContract {
    override val layoutId: Int
        get() = R.layout.activity_apply_going_doc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ApplyGoingViewModelFactory(this)
        binding.applyGoingDocViewModel = ViewModelProviders.of(this, factory).get(ApplyGoingViewModel::class.java)
    }

    override fun backApplyGoing() = finish()

}