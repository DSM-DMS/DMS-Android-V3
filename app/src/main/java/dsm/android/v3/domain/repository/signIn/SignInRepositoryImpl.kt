package dsm.android.v3.domain.repository.signIn

import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.AuthModel
import io.reactivex.Single
import retrofit2.Response

class SignInRepositoryImpl(val apiClient: ApiClient, val localStorage: LocalStorage): SignInRepository {
    override fun signIn(body: Any?): Single<Response<AuthModel>> = apiClient.signIn(body)

    override fun saveToken(token: String, access: Boolean) = localStorage.saveToken(token, access)
}