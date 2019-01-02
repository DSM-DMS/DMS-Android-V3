package dsm.android.v3.ui.signIn

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dsm.android.v3.R
import dsm.android.v3.databinding.ActivitySignInBinding
import dsm.android.v3.util.DataBindingActivity
import android.support.constraint.ConstraintLayout
import android.view.animation.AnimationUtils

class SignInActivity : DataBindingActivity<ActivitySignInBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_sign_in

    val viewModel: SignInViewModel by lazy { ViewModelProviders.of(this)[SignInViewModel::class.java] }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        val slideUp = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.slide_up
        )

        val signInConstBack = findViewById<ConstraintLayout>(R.id.signIn_constraintLayout_layout)
        signInConstBack.startAnimation(slideUp)

    }

}
