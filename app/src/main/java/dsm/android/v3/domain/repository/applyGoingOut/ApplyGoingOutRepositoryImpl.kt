package dsm.android.v3.domain.repository.applyGoingOut

import dsm.android.v3.data.local.dao.OfflineDao
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.applyGoingOut.ApplyGoingOutModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

class ApplyGoingOutRepositoryImpl(
    val apiClient: ApiClient,
    val dao: OfflineDao): ApplyGoingOutRepository {

    override fun getGoingOutInfo(): Single<Response<ApplyGoingOutModel>> = apiClient.getGoingOutInfo()

    override fun applyGoingOutDoc(body: Any?): Single<Response<Unit>> = apiClient.applyGoingOutDoc(body)

    override fun editGoingOut(body: Any?): Single<Response<Unit>> = apiClient.editGoingOut(body)

    override fun deleteGoingOut(body: Any?): Single<Response<Unit>> = apiClient.deleteGoingOut(body)

    override fun loadGoingOut(): Single<ArrayList<ApplyGoingOutModel.ApplyGoingDataModel>> = dao.getGoingOut()

    override fun saveGoingOut(applyGoingDataModel: ApplyGoingOutModel.ApplyGoingDataModel) = dao.insertGoingOut(applyGoingDataModel)
}