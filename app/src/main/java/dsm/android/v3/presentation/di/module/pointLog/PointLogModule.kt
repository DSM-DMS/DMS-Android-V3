package dsm.android.v3.presentation.di.module.pointLog

import dagger.Module
import dagger.Provides
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.repository.pointLog.PointLogRepository
import dsm.android.v3.domain.repository.pointLog.PointLogRepositoryImpl
import dsm.android.v3.presentation.di.scope.ActivityScope

@Module
class PointLogModule {
    @ActivityScope
    @Provides
    fun provideRepository(apiClient: ApiClient): PointLogRepository
            = PointLogRepositoryImpl(apiClient)
}