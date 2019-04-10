package dsm.android.v3.ui.signIn

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivitySignInBinding
import dsm.android.v3.ui.main.MainActivity
import dsm.android.v3.ui.register.RegisterActivity
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SignInActivity : DataBindingActivity<ActivitySignInBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_sign_in

    private val viewModel: SignInViewModel by lazy { ViewModelProviders.of(this).get(SignInViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        viewModel.successToastLiveEvent.observe(this, Observer { toast("로그인에 성공하였습니다") })

        viewModel.loginSuccessLiveEvent.observe(this, Observer {
            startActivity<MainActivity>()
            finish()
        })

        viewModel.doRegisterLiveEvent.observe(this, Observer { startActivity<RegisterActivity>() })

        viewModel.failedToastLiveEvent.observe(this, Observer { toast("아이디 혹은 비밀번호를 확인해 주세요") })
        viewModel.networkToastLiveEvent.observe(this, Observer { toast("네트워크 상태를 확인해주세요") })
        AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up).let {
            signIn_constraintLayout_layout.startAnimation(it)
        }

        signIn_id_et.setOnFocusChangeListener { v, hasFocus ->
            signIn_id_tv.clicked()
            signIn_pw_tv.unClicked()
            signIn_id_et.hint = ""
            signIn_pw_et.setHint(R.string.pw_et)
        }
        signIn_pw_et.setOnFocusChangeListener { v, hasFocus ->
            signIn_id_tv.unClicked()
            signIn_pw_tv.clicked()
            signIn_id_et.setHint(R.string.id_et)
            signIn_pw_et.hint = ""
        }


    }

    fun TextView.clicked() = setTextColor(ContextCompat.getColor(this@SignInActivity, R.color.colorPrimary))
    fun TextView.unClicked() = setTextColor(ContextCompat.getColor(this@SignInActivity, R.color.colorTvUnCliked))

}
