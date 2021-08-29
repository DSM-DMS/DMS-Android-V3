package dsm.android.v3.data.remote

import dsm.android.v3.presentation.model.ApplyMealResponseModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface EaesAPI {
    @GET("/apply-weekend")
    @Headers("Content-Type: application/json")
    fun getStatus():Single<Response<ApplyMealResponseModel>>

    @POST("/apply-weekend")
    @Headers("Content-Type: application/json")
    fun postStatus(@Body body:Any?):Single<Response<Unit>>

}