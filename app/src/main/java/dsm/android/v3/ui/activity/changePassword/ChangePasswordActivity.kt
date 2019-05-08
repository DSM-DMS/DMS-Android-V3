package dsm.android.v3.ui.activity.changePassword

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityChangePasswordBinding
import dsm.android.v3.presentation.di.app.BaseApp
import dsm.android.v3.presentation.viewModel.changePassword.ChangePasswordViewModel
import dsm.android.v3.util.DataBindingActivity

class ChangePasswordActivity : DataBindingActivity<ActivityChangePasswordBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_change_password

    override fun inject() = BaseApp.appComponent.injectActivity(this)
    val viewModel: ChangePasswordViewModel by lazy { ViewModelProviders.of(this)[ChangePasswordViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        viewModel.activityFinishLiveEvent.observe(this, Observer {
            finish()
        })
    }
}
