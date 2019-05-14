package dsm.android.v3.ui.activity.changePassword

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.data.local.dao.AuthDao
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.databinding.ActivityChangePasswordBinding
import dsm.android.v3.domain.repository.changePassword.ChangePasswordRepositoryImpl
import dsm.android.v3.presentation.di.app.BaseApp
import dsm.android.v3.presentation.viewModel.changePassword.ChangePasswordViewModel
import dsm.android.v3.presentation.viewModel.changePassword.ChangePasswordViewModelFactory
import dsm.android.v3.util.DataBindingActivity
import org.jetbrains.anko.toast
import javax.inject.Inject

class ChangePasswordActivity : DataBindingActivity<ActivityChangePasswordBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_change_password

    @Inject
    lateinit var apiClient: ApiClient

    @Inject
    lateinit var authDao: AuthDao

    val factory: ChangePasswordViewModelFactory by lazy { ChangePasswordViewModelFactory(ChangePasswordRepositoryImpl(apiClient, authDao)) }
    val viewModel: ChangePasswordViewModel by lazy { ViewModelProviders.of(this, factory)[ChangePasswordViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        viewModel.activityFinishLiveEvent.observe(this, Observer {
            finish()
        })
        viewModel.changeSuccessLiveEvent.observe(this, Observer { toast("비밀번호 변경이 완료되었습니다.") })
        viewModel.samePasswordLiveEvent.observe(this, Observer { toast("현재 비밀번호와 새 비밀번호가 동일합니다.") })
        viewModel.errorLiveEvent.observe(this, Observer { toast("비밀번호 변경이 제대로 완료되지 않았습니다.") })
    }
}
