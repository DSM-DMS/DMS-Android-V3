package dsm.android.v3.data.remote
import dsm.android.v3.presentation.model.ApplyMealResponseModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import retrofit2.Response

class EaseApiClient(val apiClient:EaesAPI) {
    fun getMealStatus(): Single<Response<ApplyMealResponseModel>> = apiClient.getStatus()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun postMealStatus(body:Any?): Single<Response<Unit>> = apiClient.postStatus(body)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}