package dsm.android.v3.connecter

import com.google.gson.JsonObject
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
    fun getMeal(@Path("day") day: String): Call<MealModel>


}