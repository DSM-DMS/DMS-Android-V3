package dsm.android.v3.ui.activity.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dsm.android.v3.ui.activity.main.MainActivity
import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.data.local.shared.SharedPrefStorage
import dsm.android.v3.domain.entity.Auth
import dsm.android.v3.presentation.di.app.BaseApp
import dsm.android.v3.ui.activity.signIn.AuthDatabase
import dsm.android.v3.ui.activity.signIn.SignInActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: Auth

    @Inject
    private lateinit var localStorage: LocalStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BaseApp.appComponent.injectActivity(this)
        doAsync {
            auth = AuthDatabase.getInstance(this@SplashActivity)!!.getAuthDao().getAuth()
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                localStorage.getToken().subscribe {
                    if (it == "Bearer " || auth.id.isEmpty() || auth.password.isEmpty()) {
                        startActivity<SignInActivity>()
                        finish()
                        toast("로그인이 필요합니다.").show()
                    } else {
                        startActivity<MainActivity>()
                        finish()
                    }
                }
            } else {
                toast("안드로이드 버전 업그레이드가 필요합니다.").show()
            }
        }

    }
}