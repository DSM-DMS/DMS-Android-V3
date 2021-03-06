package dsm.android.v3.domain.repository.pointLog

import dsm.android.v3.domain.entity.PointLogListModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

interface PointLogRepository {
    fun getPointLog(): Single<Response<PointLogListModel>>
}