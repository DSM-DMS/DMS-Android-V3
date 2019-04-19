package dsm.android.v3.ui.activity.applyGoingOutEdit

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityApplyGoingEditBinding
import dsm.android.v3.presentation.viewModel.applyGoingOutEdit.ApplyGoingEditViewModel
import dsm.android.v3.util.DataBindingActivity
import org.jetbrains.anko.toast

class ApplyGoingEditActivity: DataBindingActivity<ActivityApplyGoingEditBinding>(), ApplyGoingEditContract{
    override val layoutId: Int
        get() = R.layout.activity_apply_going_edit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ApplyGoingEditViewModelFactory(this)
        binding.applyGoingEditViewModel = ViewModelProviders.of(this, factory).get(ApplyGoingEditViewModel::class.java)
    }

    override fun createShortToast(text: String) = toast(text).show()

    override fun backApplyGoing() = finish()
}