package dsm.android.v3.ui.activity.splash

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import dsm.android.v3.ui.activity.main.MainActivity
import dsm.android.v3.domain.repository.splash.SplashRepository
import dsm.android.v3.ui.activity.signIn.SignInActivity
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var repository: SplashRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            startActivity<MainActivity>()
            if (repository.getToken()  == "Bearer ")
                startActivity<SignInActivity>()
        }
        finish()
    }
}