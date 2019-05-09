package dsm.android.v3.domain.repository.applyStaying

import dsm.android.v3.domain.entity.ApplyStayingModel
import io.reactivex.Single

interface ApplyStayingRepository {
    fun getStayInfo(): Single<ApplyStayingModel>

    fun applyStay(body: Any?): Single<Unit>
}