package dsm.android.v3.domain.repository.applyMeal

import dsm.android.v3.presentation.model.ApplyMealResponseModel
import io.reactivex.Single
import retrofit2.Response

interface ApplyMealRepository {
    fun getStatus(): Single<Response<ApplyMealResponseModel>>

    fun applyStatus(body:Any?): Single<Response<Unit>>
}