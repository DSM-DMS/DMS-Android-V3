package dsm.android.v3.data.remote

import retrofit2.http.*
import com.google.gson.JsonObject
import dsm.android.v3.domain.entity.*
import dsm.android.v3.presentation.model.NoticeDescriptionModel
import dsm.android.v3.domain.entity.NoticeListModel
import dsm.android.v3.domain.entity.RulesModel
import io.reactivex.Single
import retrofit2.Response

interface API {
    //로그인
    @POST("account/auth")
    fun signIn(@Body body: JsonObject): Single<Response<AuthModel>>

    //회원가입
    @POST("account/signup")
    fun signUp(@Body body: JsonObject): Single<Response<Unit>>

    @GET("meal/{day}")
    fun getMeal(@Path("day") day: String): Single<Response<JsonObject>>

    @GET("/notice")
    fun getNoticeList(): Single<Response<NoticeListModel>>

    @GET("/rule")
    fun getRulesList(): Single<Response<RulesModel>>

    @GET("/notice/{notice_id}")
    fun getNoticeDescription(@Path("notice_id") notice_id: String): Single<Response<NoticeDescriptionModel>>

    @GET("/rule/{rule_id}")
    fun getRulesDescription(@Path("rule_id") rule_id: String): Single<Response<NoticeDescriptionModel>>

    @GET("info/point")
    fun getPointLog(): Single<Response<PointLogResponseModel>>

    @PATCH("account/pw")
    fun changePw(@Body body: Any?): Single<Response<Unit>>

    @GET("/apply/extension/map/{time}/{class_num}")
    @Headers("Content-Type: application/json")
    fun getMap(@Path("time") time: Int, @Path("class_num") classNum: Int): Single<Response<ExtensionModel>>

    @POST("/apply/extension/{time}")
    @Headers("Content-Type: application/json")
    fun applyExtension(@Path("time") time: Int, @Body body: HashMap<String, Int>): Single<Response<Unit>>

    @DELETE("/apply/extension/{time}")
    @Headers("Content-Type: application/json")
    fun deleteExtension(@Path("time") time: Int): Single<Response<Unit>>

    @GET("/apply/stay")
    @Headers("Content-Type: application/json")
    fun getStayInfo(): Single<Response<ApplyStayingModel>>

    @POST("/apply/stay")
    @Headers("Content-Type: application/json")
    fun applyStay(@Body body: Any?): Single<Response<Unit>>

    @GET("/apply/goingout")
    @Headers("Content-Type: application/json")
    fun getGoingOutInfo(): Single<Response<ApplyGoingOutModel>>

    @POST("/apply/goingout")
    @Headers("Content-Type: application/json")
    fun applyGoingOutDoc(@Body body: Any?): Single<Response<Unit>>

    @PATCH("/apply/goingout")
    @Headers("Content-Type: application/json")
    fun editGoingOut(@Body body: Any?): Single<Response<Unit>>

    @GET("/apply/music")
    fun getMusic(): Single<Response<ApplyMusicModel>>

    @POST("/apply/music")
    fun applyMusic(@Body body: Any?): Single<Response<Unit>>

    @HTTP(method = "DELETE", path = "/apply/music", hasBody = true)
    fun deleteMusic(@Body body: Any?): Single<Response<Unit>>

    @HTTP(method = "DELETE", path = "/apply/goingout", hasBody = true)
    @Headers("Content-Type: application/json")
    fun deleteGoingOut(@Body body: Any?): Single<Response<Unit>>

    @GET("/info/basic")
    @Headers("Content-Type: application/json")
    fun getBasicInfo(): Single<Response<MyPageInfoModel>>

    @POST("/report/facility")
    @Headers("Content-Type: application/json")
    fun reportInstitution(@Body body: Any?): Single<Response<Unit>>

    @POST("/report/bug/2")
    @Headers("Content-Type: application/json")
    fun reportBug(@Body body: Any?): Single<Response<Unit>>

}
