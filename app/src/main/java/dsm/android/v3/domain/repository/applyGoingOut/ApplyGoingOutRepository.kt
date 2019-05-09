package dsm.android.v3.domain.repository.applyGoingOut

import dsm.android.v3.domain.entity.ApplyGoingOutModel
import io.reactivex.Single

interface ApplyGoingOutRepository {
    fun getGoingOutInfo(): Single<ApplyGoingOutModel>

    fun applyGoingOutDoc(body: Any?): Single<Unit>

    fun editGoingOut(body: Any?): Single<Unit>

    fun deleteGoingOut(body: Any?): Single<Unit>
}