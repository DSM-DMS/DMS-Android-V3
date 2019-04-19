package dsm.android.v3.data.remote

import retrofit2.Call
import retrofit2.http.*
import com.google.gson.JsonObject
import dsm.android.v3.data.entity.*
import dsm.android.v3.presentation.model.NoticeDescriptionModel
import dsm.android.v3.data.entity.NoticeListModel
import dsm.android.v3.data.entity.RulesModel

interface API {

    //로그인
    @POST("account/auth")
    fun signIn(@Body body: JsonObject): Call<AuthModel>

    //회원가입
    @POST("account/signup")
    fun signUp(@Body body: JsonObject): Call<Void>

    @GET("meal/{day}")
    fun getMeal(@Path("day") day: String): Call<JsonObject>

    @GET("/notice")
    fun getNoticeList(): Call<NoticeListModel>

    @GET("/rule")
    fun getRulesList(): Call<RulesModel>

    @GET("/notice/{notice_id}")
    fun getNoticeDescription(@Path("notice_id") notice_id: String): Call<NoticeDescriptionModel>

    @GET("/rule/{rule_id}")
    fun getRulesDescription(@Path("rule_id") rule_id: String): Call<NoticeDescriptionModel>

    @GET("info/point")
    fun getPointLog(): Call<PointLogResponseModel>

    @PATCH("account/pw")
    fun changePw(@Body body: JsonObject): Call<Void>

    @GET("/apply/extension/map/{time}/{class_num}")
    @Headers("Content-Type: application/json")
    fun getMap(@Path("time") time: Int, @Path("class_num") classNum: Int): Call<ExtensionModel>

    @POST("/apply/extension/{time}")
    @Headers("Content-Type: application/json")
    fun applyExtension(@Path("time") time: Int, @Body body: HashMap<String, Int>): Call<Unit>

    @DELETE("/apply/extension/{time}")
    @Headers("Content-Type: application/json")
    fun deleteExtension(@Path("time") time: Int): Call<Unit>

    @GET("/apply/stay")
    @Headers("Content-Type: application/json")
    fun getStayInfo(): Call<ApplyStayingModel>

    @POST("/apply/stay")
    @Headers("Content-Type: application/json")
    fun applyStay(@Body body: Any?): Call<Unit>

    @GET("/apply/goingout")
    @Headers("Content-Type: application/json")
    fun getGoingOutInfo(): Call<ApplyGoingOut>

    @POST("/apply/goingout")
    @Headers("Content-Type: application/json")
    fun applyGoingOutDoc(@Body body: Any?): Call<Unit>

    @PATCH("/apply/goingout")
    @Headers("Content-Type: application/json")
    fun editGoingOut(@Body body: Any?): Call<Unit>

    @GET("/apply/music")
    fun getMusic(): Call<ApplyMusicModel>

    @POST("/apply/music")
    fun applyMusic(@Body body: Any?): Call<Unit>

    @HTTP(method = "DELETE", path = "/apply/music", hasBody = true)
    fun deleteMusic(@Body body: Any?): Call<Void>

    @HTTP(method = "DELETE", path = "/apply/goingout", hasBody = true)
    @Headers("Content-Type: application/json")
    fun deleteGoingOut(@Body body: Any?): Call<Unit>

    @GET("/info/basic")
    @Headers("Content-Type: application/json")
    fun getBasicInfo(): Call<MyPageInfoModel>

    @POST("/report/facility")
    @Headers("Content-Type: application/json")
    fun reportInstitution(@Body body: Any?): Call<Unit>

    @POST("/report/bug/2")
    @Headers("Content-Type: application/json")
    fun reportBug(@Body body: Any?): Call<Unit>

}
