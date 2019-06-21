package dsm.android.v3.domain.repository.changePassword

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Response

interface ChangePasswordRepository {
    fun changePw(body: Any?): Single<Response<Unit>>
}