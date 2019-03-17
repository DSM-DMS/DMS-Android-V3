package dsm.android.v3.connecter

import com.google.gson.JsonObject
import dsm.android.v3.model.MealModel
import dsm.android.v3.model.Notice.NoticeDescriptionModel
import dsm.android.v3.model.Notice.NoticeListModel
import dsm.android.v3.model.Notice.RulesModel
import dsm.android.v3.model.PointLogResponseModel
import retrofit2.Call
import retrofit2.http.*
import retrofit2.*
import dsm.android.v3.model.*

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
    fun getPointLog(@Header("Authorization") token: String): Call<PointLogResponseModel>

    @PATCH("account/pw")
    fun changePw(@Header("Authorization") token: String, @Body body: JsonObject): Call<Void>
}