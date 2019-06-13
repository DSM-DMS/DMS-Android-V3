package dsm.android.v3.presentation.di.module.pointLog

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import dsm.android.v3.data.local.dao.PointLogDao
import dsm.android.v3.data.local.database.PointLogDatabase
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.pointLog.PointLogRepository
import dsm.android.v3.domain.repository.pointLog.PointLogRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope

@Module
class PointLogModule {
    @ActivityScope
    @Provides
    fun provideDataBase(context: Context) : PointLogDatabase
            = Room.databaseBuilder(context, PointLogDatabase::class.java, "pointLog.db").build()

    @ActivityScope
    @Provides
    fun provideDao(database: PointLogDatabase): PointLogDao
            = database.getPointLogDao()

    @ActivityScope
    @Provides
    fun provideRepository(apiClient: ApiClient, dao: PointLogDao): PointLogRepository
            = PointLogRepositoryImpl(apiClient, dao)
}