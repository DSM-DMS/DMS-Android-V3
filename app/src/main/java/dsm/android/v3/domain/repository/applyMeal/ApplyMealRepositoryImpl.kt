package dsm.android.v3.domain.repository.applyMeal

import dsm.android.v3.data.remote.EaseApiClient
import dsm.android.v3.presentation.model.ApplyMealResponseModel
import io.reactivex.Single
import retrofit2.Response

class ApplyMealRepositoryImpl(val apiClient: EaseApiClient) : ApplyMealRepository {
    override fun getStatus(): Single<Response<ApplyMealResponseModel>> = apiClient.getMealStatus()

    override fun applyStatus(body:Any?): Single<Response<Unit>> = apiClient.postMealStatus(body)

}