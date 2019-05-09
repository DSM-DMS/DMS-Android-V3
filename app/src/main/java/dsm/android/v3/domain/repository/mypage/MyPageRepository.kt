package dsm.android.v3.domain.repository.mypage

import dsm.android.v3.domain.entity.MyPageInfoModel
import io.reactivex.Single

interface MyPageRepository {
    fun getBasicInfo(): Single<MyPageInfoModel>

    fun reportBug(body: Any?): Single<Unit>

    fun reportInstitution(body: Any?): Single<Unit>

    fun logout()
}