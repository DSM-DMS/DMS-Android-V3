package dsm.android.v3.connecter

import retrofit2.http.*
import retrofit2.*
import dsm.android.v3.model.*

interface API {

    //로그인
    @POST("student/auth")
    @Headers("Content-Type: application/json")
    fun signIn(@Body body: Any?): Call<AuthModel>

    //회원가입
    @POST("student/signup")
    @Headers("Content-Type: application/json")
    fun signUp(@Body body: Any?): Call<Void>

}