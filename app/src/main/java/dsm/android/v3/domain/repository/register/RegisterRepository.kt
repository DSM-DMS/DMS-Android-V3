package dsm.android.v3.domain.repository.register

import com.google.gson.JsonObject
import io.reactivex.Single

interface RegisterRepository {
    fun signUp(body: JsonObject): Single<Unit>
}