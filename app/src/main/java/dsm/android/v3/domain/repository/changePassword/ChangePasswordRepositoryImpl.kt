package dsm.android.v3.domain.repository.changePassword

import dsm.android.v3.data.remote.ApiClient
import io.reactivex.Single
import retrofit2.Response

class ChangePasswordRepositoryImpl(val apiClient: ApiClient): ChangePasswordRepository {
    override fun changePw(body: Any?): Single<Response<Unit>> = apiClient.changePw(body)
}