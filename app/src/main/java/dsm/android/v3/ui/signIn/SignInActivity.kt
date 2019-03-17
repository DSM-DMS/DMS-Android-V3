package dsm.android.v3.ui.signIn

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivitySignInBinding
import dsm.android.v3.util.DataBindingActivity
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import dsm.android.v3.ui.main.MainActivity
import dsm.android.v3.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.startActivity

class SignInActivity : DataBindingActivity<ActivitySignInBinding>(), SignInNavigator {

    override val layoutId: Int
        get() = R.layout.activity_sign_in

    private val factory = SignInViewModelFactory(this)
    private val viewModel: SignInViewModel by lazy {
        ViewModelProviders.of(this, factory).get(SignInViewModel::class.java)
    }

    @SuppressLint("ResourceType", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        val slideUp = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.slide_up
        )

        signIn_constraintLayout_layout.startAnimation(slideUp)

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

    override fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun intentToRegister() = startActivity<RegisterActivity>()

    override fun intentToMain() {
        finish()
    }

    fun TextView.clicked() = setTextColor(ContextCompat.getColor(this@SignInActivity, R.color.colorPrimary))
    fun TextView.unClicked() = setTextColor(ContextCompat.getColor(this@SignInActivity, R.color.colorTvUnCliked))

}
