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
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.startActivity

class RegisterActivity : DataBindingActivity<ActivityRegisterBinding>(), RegisterNavigator {

    override val layoutId: Int
        get() = R.layout.activity_register

    private val factory = RegisterViewModelFactory(this)
    private val viewModel: RegisterViewModel by lazy {
        ViewModelProviders.of(this, factory).get(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        val slideUp = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.slide_up
        )

        val registerConstBack = findViewById<ConstraintLayout>(R.id.register_constraint)
        registerConstBack.startAnimation(slideUp)

        register_code_et.setOnFocusChangeListener { v, hasFocus ->
            register_code_tv.clicked()
            register_id_tv.unClicked()
            register_pw_tv.unClicked()
            register_pw_again_tv.unClicked()
        }
        register_id_et.setOnFocusChangeListener { v, hasFocus ->
            register_code_tv.unClicked()
            register_id_tv.clicked()
            register_pw_tv.unClicked()
            register_pw_again_tv.unClicked()
        }
        register_pw_et.setOnFocusChangeListener { v, hasFocus ->
            register_code_tv.unClicked()
            register_id_tv.unClicked()
            register_pw_tv.clicked()
            register_pw_again_tv.unClicked()
        }
        register_pw_again_et.setOnFocusChangeListener { v, hasFocus ->
            register_code_tv.unClicked()
            register_id_tv.unClicked()
            register_pw_tv.unClicked()
            register_pw_again_tv.clicked()
        }
    }

    fun TextView.clicked() = setTextColor(ContextCompat.getColor(this@RegisterActivity, R.color.colorPrimary))
    fun TextView.unClicked() = setTextColor(ContextCompat.getColor(this@RegisterActivity, R.color.colorTvUnCliked))

    override fun intentToLogin() {
        startActivity<SignInActivity>()
    }


}