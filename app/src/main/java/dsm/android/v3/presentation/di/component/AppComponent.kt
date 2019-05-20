package dsm.android.v3.presentation.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dsm.android.v3.presentation.di.app.BaseApp
import dsm.android.v3.presentation.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [(AppModule::class), (ActivityModule::class), (AndroidSupportInjectionModule::class)]
)
interface AppComponent: AndroidInjector<BaseApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApp): AppComponent.Builder
        fun build(): AppComponent
    }
}