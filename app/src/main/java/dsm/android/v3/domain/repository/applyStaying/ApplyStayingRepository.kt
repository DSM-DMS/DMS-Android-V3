package dsm.android.v3.domain.repository.applyStaying

import dsm.android.v3.domain.entity.ApplyStayingModel
import io.reactivex.Single
import retrofit2.Response

interface ApplyStayingRepository {
    fun getStayInfo(): Single<Response<ApplyStayingModel>>

    fun applyStay(body: Any?): Single<Response<Unit>>
}