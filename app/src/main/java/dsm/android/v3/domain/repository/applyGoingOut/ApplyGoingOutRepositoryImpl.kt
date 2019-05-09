package dsm.android.v3.domain.repository.applyGoingOut

import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.ApplyGoingOutModel
import io.reactivex.Single

class ApplyGoingOutRepositoryImpl(val apiClient: ApiClient): ApplyGoingOutRepository {
    override fun getGoingOutInfo(): Single<ApplyGoingOutModel> = apiClient.getGoingOutInfo()

    override fun applyGoingOutDoc(body: Any?): Single<Unit> = apiClient.applyGoingOutDoc(body)

    override fun editGoingOut(body: Any?): Single<Unit> = apiClient.editGoingOut(body)

    override fun deleteGoingOut(body: Any?): Single<Unit> = apiClient.deleteGoingOut(body)
}