package dsm.android.v3.domain.repository.applyStaying

import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.ApplyStayingModel
import io.reactivex.Single

class ApplyStayingRepositoryImpl(val apiClient: ApiClient): ApplyStayingRepository {
    override fun getStayInfo(): Single<ApplyStayingModel> = apiClient.getStayInfo()

    override fun applyStay(body: Any?): Single<Unit> = apiClient.applyStay(body)
}