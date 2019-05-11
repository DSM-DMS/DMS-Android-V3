package dsm.android.v3.domain.repository.applyGoingOut

import dsm.android.v3.domain.entity.ApplyGoingOutModel
import io.reactivex.Single
import retrofit2.Response

interface ApplyGoingOutRepository {
    fun getGoingOutInfo(): Single<Response<ApplyGoingOutModel>>

    fun applyGoingOutDoc(body: Any?): Single<Response<Unit>>

    fun editGoingOut(body: Any?): Single<Response<Unit>>

    fun deleteGoingOut(body: Any?): Single<Response<Unit>>
}