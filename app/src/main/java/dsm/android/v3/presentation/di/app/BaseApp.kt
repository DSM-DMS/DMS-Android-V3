package dsm.android.v3.presentation.di.app

import com.google.android.gms.common.util.SharedPreferencesUtils
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.presentation.di.component.DaggerAppComponent
import dsm.android.v3.util.ThemeUtil
import javax.inject.Inject

class BaseApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    @Inject
    lateinit var localStorage: LocalStorage
    override fun onCreate() {
        super.onCreate()
        ThemeUtil(localStorage).applyTheme(localStorage.getInt("darkMode"))
    }
}