package dsm.android.v3.domain.repository.register

import com.google.gson.JsonObject
import dsm.android.v3.data.remote.ApiClient
import io.reactivex.Single

class RegisterRepositoryImpl(val apiClient: ApiClient): RegisterRepository {
    override fun signUp(body: JsonObject): Single<Unit> = apiClient.signUp(body)
}