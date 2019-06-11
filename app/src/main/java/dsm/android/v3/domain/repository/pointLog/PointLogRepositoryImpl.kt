package dsm.android.v3.domain.repository.pointLog

import dsm.android.v3.data.local.dao.OfflineDao
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.pointLogList.PointLogItemModel
import dsm.android.v3.domain.entity.pointLogList.PointLogListModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

class PointLogRepositoryImpl(
    val apiClient: ApiClient,
    val dao: OfflineDao): PointLogRepository {

    override fun getPointLog(): Single<Response<PointLogListModel>> = apiClient.getPointLog()

    override fun loadPointLog(): Single<ArrayList<PointLogItemModel>> = dao.getPointLog()

    override fun savePointLog(pointLogItemModel: Array<PointLogItemModel>) = dao.insertPointLog(pointLogItemModel)
}