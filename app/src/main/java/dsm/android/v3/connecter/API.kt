package dsm.android.v3.connecter

import dsm.android.v3.ui.applyExtensionStudy.ExtensionModel
import dsm.android.v3.ui.applyGoing.ApplyGoingDataModel
import dsm.android.v3.ui.applyStaying.ApplyStayingModel
import dsm.android.v3.ui.mypage.MyPageInfoModel
import retrofit2.Call
import retrofit2.http.*

interface API {
    @GET("meal/{day}")
    fun getMeal(@Path("day") day: String): Call<MealModel>

    @GET("/apply/extension/map/{time}/{class_num}")
    @Headers("Content-Type: application/json")
    fun getMap(@Path("time") time: Int, @Path("class_num") classNum: Int): Call<ExtensionModel>

    @POST("/apply/extension/{time}")
    @Headers("Content-Type: application/json")
    fun applyExtension(@Header("Authorization") token: String, @Path("time") time: Int, @Body body: HashMap<String, Int>): Call<Unit>

    @DELETE("/apply/extension/{time}")
    @Headers("Content-Type: application/json")
    fun deleteExtension(@Header("Authorization") token: String, @Path("time") time: Int): Call<Unit>

    @GET("/apply/stay")
    @Headers("Content-Type: application/json")
    fun getStayInfo(@Header("Authorization")  token: String): Call<ApplyStayingModel>

    @POST("/apply/stay")
    @Headers("Content-Type: application/json")
    fun applyStay(@Header("Authorization") token: String, @Body body: Any?): Call<Unit>

    @GET("/apply/goingout")
    @Headers("Content-Type: application/json")
    fun getGoingOutInfo(@Header("Authorization") token: String): Call<ApplyGoingDataModel>

    @POST("/apply/goingout")
    @Headers("Content-Type: application/json")
    fun applyGoingOutDoc(@Header("Authorization") token: String, @Body body: Any?): Call<Unit>

    @HTTP(method = "DELETE", path = "/apply/goingout", hasBody = true)
    @Headers("Content-Type: application/json")
    fun deleteGoingOut(@Header("Authorization") token: String, @Body body: Any?): Call<Unit>

    @GET("/info/basic")
    @Headers("Content-Type: application/json")
    fun getBasicInfo(@Header("Authorization") token: String): Call<MyPageInfoModel>

    @POST("/report/facility")
    @Headers("Content-Type: application/json")
    fun reportInstitution(@Header("Authorization") token: String, @Body body: Any?): Call<Unit>

    @POST("/report/bug/2")
    @Headers("Content-Type: application/json")
    fun reportBug(@Header("Authorization") token: String, @Body body: Any?): Call<Unit>

}
