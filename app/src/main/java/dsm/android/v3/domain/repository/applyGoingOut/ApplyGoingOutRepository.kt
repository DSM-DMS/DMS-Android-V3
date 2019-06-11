package dsm.android.v3.domain.repository.applyGoingOut

import dsm.android.v3.domain.entity.applyGoingOut.ApplyGoingOutModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

interface ApplyGoingOutRepository {
    fun getGoingOutInfo(): Single<Response<ApplyGoingOutModel>>

    fun applyGoingOutDoc(body: Any?): Single<Response<Unit>>

    fun editGoingOut(body: Any?): Single<Response<Unit>>

    fun deleteGoingOut(body: Any?): Single<Response<Unit>>

    fun loadGoingOut(): Single<ArrayList<ApplyGoingOutModel.ApplyGoingDataModel>>

    fun saveGoingOut(applyGoingDataModel: ApplyGoingOutModel.ApplyGoingDataModel)
}