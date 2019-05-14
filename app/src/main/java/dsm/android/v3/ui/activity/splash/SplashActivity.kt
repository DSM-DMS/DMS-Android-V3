package dsm.android.v3.ui.activity.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.support.DaggerAppCompatActivity
import dsm.android.v3.data.local.dao.AuthDao
import dsm.android.v3.ui.activity.main.MainActivity
import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.domain.entity.Auth
import dsm.android.v3.presentation.di.app.BaseApp
import dsm.android.v3.ui.activity.signIn.SignInActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    private lateinit var auth: Auth

    @Inject
    lateinit var localStorage: LocalStorage

    @Inject
    lateinit var authDao: AuthDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            auth = authDao.getAuth()
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                if (localStorage.getToken()  == "Bearer " || auth.id.isEmpty() || auth.password.isEmpty()) {
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