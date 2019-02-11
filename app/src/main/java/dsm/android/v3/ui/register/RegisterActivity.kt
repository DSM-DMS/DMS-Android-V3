package dsm.android.v3.ui.register

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.TextView
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivityRegisterBinding
import dsm.android.v3.ui.signIn.SignInActivity
import dsm.android.v3.util.DataBindingActivity
import org.jetbrains.anko.startActivity

class RegisterActivity : DataBindingActivity<ActivityRegisterBinding>(), RegisterNavigator {

    override val layoutId: Int
        get() = R.layout.activity_register

    private val factory =RegisterViewModelFactory(this)
    private val viewModel: RegisterViewModel by lazy { ViewModelProviders.of(this, factory).get(RegisterViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        val slideUp = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.slide_up
        )

        val registerConstBack = findViewById<ConstraintLayout>(R.id.register_constraint)
        registerConstBack.startAnimation(slideUp)

        val registerCode = findViewById<TextView>(R.id.register_code_tv)
        val registerId = findViewById<TextView>(R.id.register_id_tv)
        val registerPw = findViewById<TextView>(R.id.register_pw_tv)
        val registerPwConfirm = findViewById<TextView>(R.id.register_pw_again_tv)
        val etRegisterCode = findViewById<EditText>(R.id.register_code_et)

    }

    fun clicked() = ContextCompat.getColor(this, R.color.colorPrimary)
    fun unClicked() = ContextCompat.getColor(this, R.color.colorTvUnCliked)

    override fun success(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fail(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signUpPost() {
        startActivity<SignInActivity>()
    }



}