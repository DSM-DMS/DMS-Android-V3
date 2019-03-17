package dsm.android.v3.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dsm.android.v3.R
import dsm.android.v3.ui.main.MainActivity
import dsm.android.v3.ui.signIn.Auth
import dsm.android.v3.ui.signIn.AuthDatabase
import dsm.android.v3.ui.signIn.SignInActivity
import dsm.android.v3.util.getToken
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: Auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        doAsync {
            auth = AuthDatabase.getInstance(this@SplashActivity)!!.getAuthDao().getAuth()
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                if (getToken(this@SplashActivity) == "Bearer " || auth.id.isEmpty() || auth.password.isEmpty()) {
                    startActivity<SignInActivity>()
                    finish()
                    toast("로그인이 필요합니다.").show()
                } else {
                    startActivity<MainActivity>()
                    finish()
                }
            } else {
                toast("안드로이드 버전 업그레이드가 필요합니다.").show()
            }
        }

    }
}