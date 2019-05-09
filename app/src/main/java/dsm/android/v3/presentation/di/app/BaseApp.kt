package dsm.android.v3.presentation.di.app

import android.app.Application
import dsm.android.v3.presentation.di.component.AppComponent
import dsm.android.v3.presentation.di.module.ApiModule
import dsm.android.v3.presentation.di.module.AppModule
import dsm.android.v3.presentation.di.module.NetworkModule

class BaseApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        val baseUrl = "https://api.github.com/"
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule(baseUrl))
            .apiModule(ApiModule())
            .appModule(AppModule(this))
            .build()
    }
}