package dsm.android.v3.domain.repository.pointLog

import dsm.android.v3.data.local.dao.PointLogDao
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.PointLogListModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

class PointLogRepositoryImpl(
    val apiClient: ApiClient,
    val dao: PointLogDao): PointLogRepository {

    override fun getPointLog(): Single<Response<PointLogListModel>> = apiClient.getPointLog()

    override fun loadPointLog(): Flowable<PointLogListModel> = dao.getAll()

    override fun savePointLog(pointLogListModel: PointLogListModel) = dao.insertAll(pointLogListModel)
}