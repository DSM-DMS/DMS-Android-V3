package dsm.android.v3.domain.repository.bugReport

import io.reactivex.Single
import retrofit2.Response

interface BugReportRepository {
    fun reportBug(body: Any?): Single<Response<Unit>>
}