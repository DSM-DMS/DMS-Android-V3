package dsm.android.v3.domain.repository.signIn

import com.google.gson.JsonObject
import dsm.android.v3.domain.entity.AuthModel
import io.reactivex.Single
import retrofit2.Response

interface SignInRepository {
    fun signIn(body: Any?): Single<Response<AuthModel>>

    fun saveToken(token: String, access: Boolean)

    fun saveDb(id: String, pw: String)
}