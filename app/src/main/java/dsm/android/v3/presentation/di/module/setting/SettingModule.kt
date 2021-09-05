package dsm.android.v3.presentation.di.module.setting

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.domain.repository.setting.SettingRepository
import dsm.android.v3.domain.repository.setting.SettingRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.viewModel.setting.SettingViewModelFactory

@Module
class SettingModule {

    @ActivityScope
    @Provides
    fun provideRepository(localStorage: LocalStorage): SettingRepository =
        SettingRepositoryImpl(localStorage)

    @ActivityScope
    @Provides
    fun provideViewFactory(
        repository: SettingRepository
    ): SettingViewModelFactory = SettingViewModelFactory(repository)
}