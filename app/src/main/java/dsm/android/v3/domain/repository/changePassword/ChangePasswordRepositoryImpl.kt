package dsm.android.v3.domain.repository.changePassword

import com.google.gson.JsonObject
import dsm.android.v3.data.local.dao.AuthDao
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.Auth
import io.reactivex.Single
import retrofit2.Response

class ChangePasswordRepositoryImpl(val apiClient: ApiClient, val authDao: AuthDao): ChangePasswordRepository {
    override fun changePw(body: Any?): Single<Response<Unit>> = apiClient.changePw(body)

    override fun saveDb(pw: String) = authDao.insert(Auth(authDao.getAuth().id, pw))
}