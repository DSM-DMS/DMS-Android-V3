package dsm.android.v3.domain.repository.bugReport

import dsm.android.v3.data.remote.ApiClient
import io.reactivex.Single
import retrofit2.Response

class BugReportRepositoryImpl(val apiClient: ApiClient):
    BugReportRepository {
    override fun reportBug(body: Any?): Single<Response<Unit>> = apiClient.reportBug(body)
}