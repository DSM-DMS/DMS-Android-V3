package dsm.android.v3.data.remote

import retrofit2.http.*
import com.google.gson.JsonObject
import dsm.android.v3.domain.entity.*
import dsm.android.v3.presentation.model.NoticeDescriptionModel
import dsm.android.v3.domain.entity.NoticeListModel
import dsm.android.v3.domain.entity.RulesModel
import io.reactivex.Single

interface API {

    //로그인
    @POST("account/auth")
    fun signIn(@Body body: JsonObject): Single<AuthModel>

    //회원가입
    @POST("account/signup")
    fun signUp(@Body body: JsonObject): Single<Unit>

    @GET("meal/{day}")
    fun getMeal(@Path("day") day: String): Single<JsonObject>

    @GET("/notice")
    fun getNoticeList(): Single<NoticeListModel>

    @GET("/rule")
    fun getRulesList(): Single<RulesModel>

    @GET("/notice/{notice_id}")
    fun getNoticeDescription(@Path("notice_id") notice_id: String): Single<NoticeDescriptionModel>

    @GET("/rule/{rule_id}")
    fun getRulesDescription(@Path("rule_id") rule_id: String): Single<NoticeDescriptionModel>

    @GET("info/point")
    fun getPointLog(): Single<PointLogResponseModel>

    @PATCH("account/pw")
    fun changePw(@Body body: JsonObject): Single<Void>

    @GET("/apply/extension/map/{time}/{class_num}")
    @Headers("Content-Type: application/json")
    fun getMap(@Path("time") time: Int, @Path("class_num") classNum: Int): Single<ExtensionModel>

    @POST("/apply/extension/{time}")
    @Headers("Content-Type: application/json")
    fun applyExtension(@Path("time") time: Int, @Body body: HashMap<String, Int>): Single<Unit>

    @DELETE("/apply/extension/{time}")
    @Headers("Content-Type: application/json")
    fun deleteExtension(@Path("time") time: Int): Single<Unit>

    @GET("/apply/stay")
    @Headers("Content-Type: application/json")
    fun getStayInfo(): Single<ApplyStayingModel>

    @POST("/apply/stay")
    @Headers("Content-Type: application/json")
    fun applyStay(@Body body: Any?): Single<Unit>

    @GET("/apply/goingout")
    @Headers("Content-Type: application/json")
    fun getGoingOutInfo(): Single<ApplyGoingOutModel>

    @POST("/apply/goingout")
    @Headers("Content-Type: application/json")
    fun applyGoingOutDoc(@Body body: Any?): Single<Unit>

    @PATCH("/apply/goingout")
    @Headers("Content-Type: application/json")
    fun editGoingOut(@Body body: Any?): Single<Unit>

    @GET("/apply/music")
    fun getMusic(): Single<ApplyMusicModel>

    @POST("/apply/music")
    fun applyMusic(@Body body: Any?): Single<Unit>

    @HTTP(method = "DELETE", path = "/apply/music", hasBody = true)
    fun deleteMusic(@Body body: Any?): Single<Void>

    @HTTP(method = "DELETE", path = "/apply/goingout", hasBody = true)
    @Headers("Content-Type: application/json")
    fun deleteGoingOut(@Body body: Any?): Single<Unit>

    @GET("/info/basic")
    @Headers("Content-Type: application/json")
    fun getBasicInfo(): Single<MyPageInfoModel>

    @POST("/report/facility")
    @Headers("Content-Type: application/json")
    fun reportInstitution(@Body body: Any?): Single<Unit>

    @POST("/report/bug/2")
    @Headers("Content-Type: application/json")
    fun reportBug(@Body body: Any?): Single<Unit>

}
