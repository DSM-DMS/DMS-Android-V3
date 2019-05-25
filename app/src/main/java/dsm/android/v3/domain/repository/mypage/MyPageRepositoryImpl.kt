package dsm.android.v3.domain.repository.mypage

import dsm.android.v3.data.local.dao.AuthDao
import dsm.android.v3.data.local.shared.LocalStorage
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.MyPageInfoModel
import io.reactivex.Single
import retrofit2.Response

class MyPageRepositoryImpl(val apiClient: ApiClient): MyPageRepository {
    override fun getBasicInfo(): Single<Response<MyPageInfoModel>> = apiClient.getBasicInfo()
}