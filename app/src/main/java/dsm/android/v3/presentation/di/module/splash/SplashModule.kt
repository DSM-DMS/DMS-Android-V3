package dsm.android.v3.presentation.di.module.splash

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.domain.repository.splash.SplashRepository
import dsm.android.v3.domain.repository.splash.SplashRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope

@Module
class SplashModule {
    @ActivityScope
    @Provides
    fun provideRepository(localStorage: LocalStorage): SplashRepository
            = SplashRepositoryImpl(localStorage)

}