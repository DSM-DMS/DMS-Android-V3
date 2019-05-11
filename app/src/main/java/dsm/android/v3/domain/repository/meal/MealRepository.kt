package dsm.android.v3.domain.repository.meal

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Response

interface MealRepository {
    fun getMeal(day: String): Single<Response<JsonObject>>
}