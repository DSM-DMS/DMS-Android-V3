package dsm.android.v3.domain.repository.meal

import com.google.gson.JsonObject
import dsm.android.v3.data.local.dao.MealDao
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.meal.MealModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

class MealRepositoryImpl(
    val apiClient: ApiClient,
    val dao: MealDao
): MealRepository {
    override fun getMeal(day: String): Single<Response<JsonObject>> = apiClient.getMeal(day)

    override fun loadMeal(): Flowable<MealModel> = dao.getAll()

    override fun saveMeal(mealModel: MealModel) = dao.insertAll(mealModel)
}