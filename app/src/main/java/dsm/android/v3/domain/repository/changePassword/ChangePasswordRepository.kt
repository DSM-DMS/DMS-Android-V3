package dsm.android.v3.domain.repository.changePassword

import com.google.gson.JsonObject
import io.reactivex.Single

interface ChangePasswordRepository {
    fun changePw(body: JsonObject): Single<Void>
}