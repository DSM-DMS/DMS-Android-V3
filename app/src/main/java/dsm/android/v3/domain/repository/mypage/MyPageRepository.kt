package dsm.android.v3.domain.repository.mypage

import dsm.android.v3.domain.entity.MyPageInfoModel
import io.reactivex.Single
import retrofit2.Response

interface MyPageRepository {
    fun getBasicInfo(): Single<Response<MyPageInfoModel>>

    fun reportBug(body: Any?): Single<Response<Unit>>

    fun reportInstitution(body: Any?): Single<Response<Unit>>

    fun logout()
}