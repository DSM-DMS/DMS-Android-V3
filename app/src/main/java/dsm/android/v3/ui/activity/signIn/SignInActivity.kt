package dsm.android.v3.ui.activity.signIn

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.animation.AnimationUtils
import dsm.android.v3.R
import dsm.android.v3.data.local.dao.AuthDao
import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.databinding.ActivitySignInBinding
import dsm.android.v3.domain.repository.signIn.SignInRepositoryImpl
import dsm.android.v3.presentation.di.app.BaseApp
import dsm.android.v3.presentation.viewModel.signIn.SignInViewModel
import dsm.android.v3.presentation.viewModel.signIn.SignInViewModelFactory
import dsm.android.v3.ui.activity.main.MainActivity
import dsm.android.v3.ui.activity.register.RegisterActivity
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import javax.inject.Inject

class SignInActivity : DataBindingActivity<ActivitySignInBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_sign_in

    @Inject
    lateinit var factory: SignInViewModelFactory

    private val viewModel: SignInViewModel by lazy { ViewModelProviders.of(this, factory).get(SignInViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        viewModel.loginFailedLiveEvent.observe(this, Observer { toast("아이디 혹은 비밀번호를 확인해 주세요") })
        viewModel.networkErrorLiveEvent.observe(this, Observer { toast("네트워크 상태를 확인해주세요") })
        viewModel.doRegisterLiveEvent.observe(this, Observer { startActivity<RegisterActivity>() })
        viewModel.loginSuccessLiveEvent.observe(this, Observer {
            toast("로그인에 성공하였습니다")
            startActivity<MainActivity>()
            finish()
        })

        AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up).let {
            signIn_constraintLayout_layout.startAnimation(it)
        }
    }
}
