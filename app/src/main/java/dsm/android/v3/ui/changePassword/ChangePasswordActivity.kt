package dsm.android.v3.ui.changePassword

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityChangePasswordBinding
import dsm.android.v3.util.DataBindingActivity
import org.jetbrains.anko.toast

class ChangePasswordActivity : DataBindingActivity<ActivityChangePasswordBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_change_password

    val viewModel: ChangePasswordViewModel by lazy { ViewModelProviders.of(this)[ChangePasswordViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        viewModel.activityFinishLiveEvent.observe(this, Observer {
            finish()
        })
    }
}
