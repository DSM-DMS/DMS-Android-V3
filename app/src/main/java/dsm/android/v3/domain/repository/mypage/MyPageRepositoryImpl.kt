package dsm.android.v3.domain.repository.mypage

import dsm.android.v3.data.local.dao.OfflineDao
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.myPageInfo.MyPageInfoModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

class MyPageRepositoryImpl(
    val apiClient: ApiClient,
    val dao: OfflineDao): MyPageRepository {
    override fun getBasicInfo(): Single<Response<MyPageInfoModel>> = apiClient.getBasicInfo()

    override fun loadMyPageInfo(): Single<MyPageInfoModel> = dao.getMyPage()

    override fun saveMyPageInfo(myPageInfoModel: MyPageInfoModel) = dao.insertMyPage(myPageInfoModel)
}