package dsm.android.v3.domain.repository.register

import com.google.gson.JsonObject
import dsm.android.v3.data.remote.ApiClient
import io.reactivex.Single
import retrofit2.Response

class RegisterRepositoryImpl(val apiClient: ApiClient): RegisterRepository {
    override fun signUp(body: Any?): Single<Response<Unit>> = apiClient.signUp(body)
}