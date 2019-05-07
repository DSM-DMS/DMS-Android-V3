package dsm.android.v3.presentation.di.component

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import dagger.Component
import dsm.android.v3.presentation.di.module.ApiModule
import dsm.android.v3.presentation.di.module.AppModule
import dsm.android.v3.presentation.di.module.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class),(NetworkModule::class),(ApiModule::class)])
interface AppComponent{
    fun injectActivity(activity: Activity)

    fun injectFragment(activity: Fragment)
}