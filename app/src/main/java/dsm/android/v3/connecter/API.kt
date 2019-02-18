package dsm.android.v3.connecter

import com.google.gson.JsonObject
import dsm.android.v3.model.MealModel
import dsm.android.v3.model.PointLogResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface API {
    @GET("meal/{day}")
    fun getMeal(@Path("day") day: String): Call<MealModel>

    @GET("info/point")
    fun getPointLog(@Header("Authorization") token: String): Call<PointLogResponseModel>
}