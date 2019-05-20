package dsm.android.v3.domain.repository.institutionReport

import dsm.android.v3.data.remote.ApiClient
import io.reactivex.Single
import retrofit2.Response

class InstitutionReportRepositoryImpl(val apiClient: ApiClient):
    InstitutionReportRepository {
    override fun reportInstitution(body: Any?): Single<Response<Unit>> = apiClient.reportInstitution(body)
}