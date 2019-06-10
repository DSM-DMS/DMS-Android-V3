package dsm.android.v3.domain.repository.pointLog

import dsm.android.v3.domain.entity.pointLogList.PointLogListModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

interface PointLogRepository {
    fun getPointLog(): Single<Response<PointLogListModel>>

    fun loadPointLog(): Flowable<PointLogListModel>

    fun savePointLog(pointLogListModel: PointLogListModel)
}