package dsm.android.v3.presentation.di.module.applyExtensionStudy

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import dsm.android.v3.data.local.dao.ApplyExtensionStudyDao
import dsm.android.v3.data.local.database.ApplyExtensionStudyDatabase
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.applyExtensionStudy.ApplyExtensionStudyRepository
import dsm.android.v3.domain.repository.applyExtensionStudy.ApplyExtensionStudyRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.viewModel.applyExtensionStudy.ApplyExtensionStudyViewModelFactory

@Module
class ApplyExtensionStudyModule {

    @ActivityScope
    @Provides
    fun provideDataBase(context: Context) : ApplyExtensionStudyDatabase
            = Room.databaseBuilder(context, ApplyExtensionStudyDatabase::class.java, "extension.db").build()

    @ActivityScope
    @Provides
    fun provideDao(database: ApplyExtensionStudyDatabase): ApplyExtensionStudyDao
            = database.getApplyExtensionStudyDao()


    @ActivityScope
    @Provides
    fun provideRepository(apiClient: ApiClient, dao: ApplyExtensionStudyDao): ApplyExtensionStudyRepository
            = ApplyExtensionStudyRepositoryImpl(apiClient, dao)

    @ActivityScope
    @Provides
    fun provideViewModelFactory(repository: ApplyExtensionStudyRepository): ApplyExtensionStudyViewModelFactory
            = ApplyExtensionStudyViewModelFactory(repository)
}