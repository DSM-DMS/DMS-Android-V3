package dsm.android.v3.domain.repository.pointLog

import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.PointLogResponseModel
import io.reactivex.Single

class PointLogRepositoryImpl(val apiClient: ApiClient): PointLogRepository {
    override fun getPointLog(): Single<PointLogResponseModel> = apiClient.getPointLog()
}