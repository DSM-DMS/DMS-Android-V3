package dsm.android.v3.domain.repository.pointLog

import dsm.android.v3.domain.entity.PointLogResponseModel
import io.reactivex.Single

interface PointLogRepository {
    fun getPointLog(): Single<PointLogResponseModel>
}