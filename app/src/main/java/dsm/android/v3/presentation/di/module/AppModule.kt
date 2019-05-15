package dsm.android.v3.presentation.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dsm.android.v3.presentation.di.app.BaseApp
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: BaseApp): Context = application

    @Provides
    @Singleton
    fun provideApplication(app: BaseApp): Application = app
}