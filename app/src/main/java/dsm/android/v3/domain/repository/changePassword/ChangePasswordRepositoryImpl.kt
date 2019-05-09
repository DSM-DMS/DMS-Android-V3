package dsm.android.v3.domain.repository.changePassword

import com.google.gson.JsonObject
import dsm.android.v3.data.remote.ApiClient
import io.reactivex.Single

class ChangePasswordRepositoryImpl(val apiClient: ApiClient): ChangePasswordRepository {
    override fun changePw(body: JsonObject): Single<Void> = apiClient.changePw(body)
}