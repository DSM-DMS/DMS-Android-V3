package dsm.android.v3.domain.repository.meal

import com.google.gson.JsonObject
import dsm.android.v3.data.remote.ApiClient
import io.reactivex.Single
import retrofit2.Response

class MealRepositoryImpl(
    val apiClient: ApiClient
): MealRepository {
    override fun getMeal(day: String): Single<Response<JsonObject>> = apiClient.getMeal(day)
}