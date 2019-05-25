package dsm.android.v3.domain.repository.notice

import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.NoticeListModel
import dsm.android.v3.domain.entity.RulesModel
import dsm.android.v3.presentation.model.NoticeDescriptionModel
import io.reactivex.Single
import retrofit2.Response

class NoticeRepositoryImpl(val apiClient: ApiClient): NoticeRepository {
    override fun getNoticeList(): Single<Response<NoticeListModel>> = apiClient.getNoticeList()

    override fun getRulesList(): Single<Response<NoticeListModel>> = apiClient.getRulesList()

    override fun getNoticeDescription(notice_id: String): Single<Response<NoticeDescriptionModel>> = apiClient.getNoticeDescription(notice_id)

    override fun getRulesDescription(rule_id: String): Single<Response<NoticeDescriptionModel>> = apiClient.getRulesDescription(rule_id)
}