package dsm.android.v3.domain.repository.applyStaying

import dsm.android.v3.data.local.dao.ApplyStayingDao
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.ApplyStayingModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

class ApplyStayingRepositoryImpl(
    val apiClient: ApiClient,
    val dao: ApplyStayingDao
): ApplyStayingRepository {
    override fun getStayInfo(): Single<Response<ApplyStayingModel>> = apiClient.getStayInfo()

    override fun applyStay(body: Any?): Single<Response<Unit>> = apiClient.applyStay(body)

    override fun loadStaying(): Flowable<ApplyStayingModel> = dao.getAll()

    override fun saveStaying(applyStayingModel: ApplyStayingModel) = dao.insertAll(applyStayingModel)
}