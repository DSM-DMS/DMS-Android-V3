package dsm.android.v3.ui.activity.register

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityRegisterBinding
import dsm.android.v3.presentation.viewModel.register.RegisterViewModel
import dsm.android.v3.util.DataBindingActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : DataBindingActivity<ActivityRegisterBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_register

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProviders.of(this).get(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        viewModel.registerFinishedLiveEvent.observe(this, Observer {
            Toast.makeText(baseContext, "회원가입 성공", Toast.LENGTH_SHORT).show()
            finish()
        })

        viewModel.wrongUuidLiveEvent.observe(this, Observer { toast("유효하지 않은 uuid") })
        viewModel.sameIdLiveEvent.observe(this, Observer { toast("중복된 ID") })
        viewModel.wrongPwLiveEvent.observe(this, Observer { toast("비밀번호가 서로 다릅니다") })
        viewModel.badNetworkLiveEvent.observe(this, Observer { toast("회원가입 실패") })

        AnimationUtils.loadAnimation(baseContext, R.anim.slide_up).let {
            register_constraint.startAnimation(it)
        }
    }
}