package dsm.android.v3.ui.applyGoingDoc

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityApplyGoingDocBinding
import dsm.android.v3.util.DataBindingActivity
import org.jetbrains.anko.toast

class ApplyGoingDocActivity : DataBindingActivity<ActivityApplyGoingDocBinding>(),
    ApplyGoingDocContract {
    override val layoutId: Int
        get() = R.layout.activity_apply_going_doc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ApplyGoingDocViewModelFactory(this)
        binding.applyGoingDocViewModel = ViewModelProviders.of(this, factory).get(ApplyGoingDocViewModel::class.java)
    }

    override fun createShortToast(text: String) = toast(text).show()

    override fun backApplyGoing() = finish()
}