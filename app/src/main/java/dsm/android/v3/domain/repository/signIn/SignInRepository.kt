package dsm.android.v3.domain.repository.signIn

import com.google.gson.JsonObject
import dsm.android.v3.domain.entity.AuthModel
import io.reactivex.Single

interface SignInRepository {
    fun signIn(body: JsonObject): Single<AuthModel>

    fun saveToken(token: String)

    fun saveDb(id: String, pw: String)
}