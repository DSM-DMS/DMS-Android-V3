package dsm.android.v3.domain.repository.institutionReport

import io.reactivex.Single
import retrofit2.Response

interface InstitutionReportRepository {
    fun reportInstitution(body: Any?): Single<Response<Unit>>
}