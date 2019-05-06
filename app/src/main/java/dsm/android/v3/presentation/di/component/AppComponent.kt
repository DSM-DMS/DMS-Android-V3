package dsm.android.v3.presentation.di.component

import dagger.Component
import dsm.android.v3.presentation.di.app.BaseApp
import dsm.android.v3.presentation.di.module.ApiModule
import dsm.android.v3.presentation.di.module.AppModule
import dsm.android.v3.presentation.di.module.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class),(NetworkModule::class),(ApiModule::class)])
interface AppComponent{
    fun inject(baseApp : BaseApp)
}