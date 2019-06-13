package dsm.android.v3.presentation.di.module.applyGoingOut

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dsm.android.v3.data.local.dao.ApplyGoingOutDao
import dsm.android.v3.data.local.database.ApplyGoingOutDatabase
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.applyExtensionStudy.ApplyExtensionStudyRepository
import dsm.android.v3.domain.repository.applyExtensionStudy.ApplyExtensionStudyRepositoryImpl
import dsm.android.v3.domain.repository.applyGoingOut.ApplyGoingOutRepository
import dsm.android.v3.domain.repository.applyGoingOut.ApplyGoingOutRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.di.scope.FragmentScope
import dsm.android.v3.presentation.viewModel.applyExtensionStudy.ApplyExtensionStudyViewModelFactory
import dsm.android.v3.presentation.viewModel.applyGoingOut.ApplyGoingViewModelFactory
import dsm.android.v3.ui.fragment.applyGoingOut.ApplyGoingFragment
import dsm.android.v3.ui.fragment.applyGoingOutDoc.ApplyGoingDocFragment
import dsm.android.v3.ui.fragment.applyGoingOutEdit.ApplyGoingEditFragment
import dsm.android.v3.ui.fragment.applyGoingOutLog.ApplyGoingLogFragment

@Module
abstract class ApplyGoingOutModule {

    @Module
    companion object {
        @ActivityScope
        @JvmStatic
        @Provides
        fun provideDataBase(context: Context) : ApplyGoingOutDatabase
                = Room.databaseBuilder(context, ApplyGoingOutDatabase::class.java, "goingOut.db").build()

        @ActivityScope
        @JvmStatic
        @Provides
        fun provideDao(database: ApplyGoingOutDatabase): ApplyGoingOutDao
                = database.getApplyGoingOutDao()

        @ActivityScope
        @JvmStatic
        @Provides
        fun provideRepository(apiClient: ApiClient, dao: ApplyGoingOutDao): ApplyGoingOutRepository
                = ApplyGoingOutRepositoryImpl(apiClient, dao)

        @ActivityScope
        @JvmStatic
        @Provides
        fun provideViewModelFactory(repository: ApplyGoingOutRepository): ApplyGoingViewModelFactory
                = ApplyGoingViewModelFactory(repository)
    }

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun applyGoingOutFragment(): ApplyGoingFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun applyGoingOutDocFragment(): ApplyGoingDocFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun applyGoingOutEditFragment(): ApplyGoingEditFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun applyGoingOutLogFragment(): ApplyGoingLogFragment
}