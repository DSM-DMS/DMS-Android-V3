package dsm.android.v3.presentation.di.module.applyStaying

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import dsm.android.v3.data.local.dao.ApplyStayingDao
import dsm.android.v3.data.local.database.ApplyStayingDatabase
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.applyStaying.ApplyStayingRepository
import dsm.android.v3.domain.repository.applyStaying.ApplyStayingRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope
import dsm.android.v3.presentation.viewModel.applyStaying.ApplyStayingViewModelFactory

@Module
class ApplyStayingModule {

    @ActivityScope
    @Provides
    fun provideDataBase(context: Context) : ApplyStayingDatabase
            = Room.databaseBuilder(context, ApplyStayingDatabase::class.java, "staying.db").build()

    @ActivityScope
    @Provides
    fun provideDao(database: ApplyStayingDatabase): ApplyStayingDao
            = database.getApplyStayingDao()

    @ActivityScope
    @Provides
    fun provideRepository(apiClient: ApiClient, dao: ApplyStayingDao): ApplyStayingRepository
            = ApplyStayingRepositoryImpl(apiClient, dao)

    @ActivityScope
    @Provides
    fun provideViewModelFactory(repository: ApplyStayingRepository): ApplyStayingViewModelFactory
            = ApplyStayingViewModelFactory(repository)
}