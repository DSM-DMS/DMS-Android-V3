package dsm.android.v3.presentation.di.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import dsm.android.v3.presentation.di.component.DaggerAppComponent

class BaseApp : DaggerApplication()  {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>
            = DaggerAppComponent.builder().application(this).build()
}