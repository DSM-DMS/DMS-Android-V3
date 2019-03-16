package dsm.android.v3.connecter

import dsm.android.v3.model.MealModel
import dsm.android.v3.model.NoticeListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("meal/{day}")
    fun getMeal(@Path("day") day: String): Call<MealModel>

    @GET("/notice")
    fun getNoticeList () : Call<Array<NoticeListModel>>
}