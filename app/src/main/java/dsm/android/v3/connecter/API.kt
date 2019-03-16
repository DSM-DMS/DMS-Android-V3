package dsm.android.v3.connecter

import dsm.android.v3.model.MealModel
import dsm.android.v3.model.Notice.NoticeDescriptionModel
import dsm.android.v3.model.Notice.NoticeListModel
import dsm.android.v3.model.Notice.RulesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("meal/{day}")
    fun getMeal(@Path("day") day: String): Call<MealModel>

    @GET("/notice")
    fun getNoticeList () : Call<NoticeListModel>

    @GET("/rule")
    fun getRulesList () : Call<RulesModel>

    @GET("/notice/{notice_id}")
    fun getNoticeDescription (@Path("notice_id") notice_id : String) : Call<NoticeDescriptionModel>

    @GET("/rule/{rule_id}")
    fun getRulesDescription (@Path("rule_id") rule_id : String) : Call<NoticeDescriptionModel>

}