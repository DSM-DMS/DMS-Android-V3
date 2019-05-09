package dsm.android.v3.domain.repository.signIn

import com.google.gson.JsonObject
import dsm.android.v3.data.local.dao.AuthDao
import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.Auth
import dsm.android.v3.domain.entity.AuthModel
import io.reactivex.Single

class SignInRepositoryImpl(val apiClient: ApiClient, val localStorage: LocalStorage, val authDao: AuthDao): SignInRepository {
    override fun signIn(body: JsonObject): Single<AuthModel> = apiClient.signIn(body)

    override fun saveToken(token: String) = localStorage.saveToken(token)

    override fun saveDb(id: String, pw: String) = authDao.insert(Auth(id, pw))
}