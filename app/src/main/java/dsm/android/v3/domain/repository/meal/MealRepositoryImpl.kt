package dsm.android.v3.domain.repository.meal

import com.google.gson.JsonObject
import dsm.android.v3.data.remote.ApiClient
import io.reactivex.Single

class MealRepositoryImpl(val apiClient: ApiClient): MealRepository {
    override fun getMeal(day: String): Single<JsonObject> = apiClient.getMeal(day)
}