package dsm.android.v3.presentation.di.module.applyMusic

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.applyMusic.ApplyMusicRepository
import dsm.android.v3.domain.repository.applyMusic.ApplyMusicRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.di.scope.DialogFragmentScope
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.presentation.viewModel.applyMusic.ApplyMusicViewModelFactory
import dsm.android.v3.ui.dialogFragment.applyMusic.ApplyMusicDialogFragment
import dsm.android.v3.ui.fragment.applyMusic.ApplyMusicFragment
import dsm.android.v3.ui.fragment.applyMusic.ApplyMusicLogFragment

@Module
abstract class ApplyMusicModule {

    @ActivityScope
    @Provides
    fun provideRepository(apiClient: ApiClient): ApplyMusicRepository
            = ApplyMusicRepositoryImpl(apiClient)

    @ActivityScope
    @Provides
    fun provideViewModelFactory(repository: ApplyMusicRepository, application: Application): ApplyMusicViewModelFactory
            = ApplyMusicViewModelFactory(repository, application)

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun applyMusicFragment(): ApplyMusicFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun applyMusicLogFragment(): ApplyMusicLogFragment

    @DialogFragmentScope
    @ContributesAndroidInjector
    abstract fun applyMusicDialogFragment(): ApplyMusicDialogFragment
}