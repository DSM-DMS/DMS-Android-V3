package dsm.android.v3.domain.repository.notice

import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.NoticeListModel
import dsm.android.v3.domain.entity.RulesModel
import dsm.android.v3.presentation.model.NoticeDescriptionModel
import io.reactivex.Single

class NoticeRepositoryImpl(val apiClient: ApiClient): NoticeRepository {
    override fun getNoticeList(): Single<NoticeListModel> = apiClient.getNoticeList()

    override fun getRulesList(): Single<RulesModel> = apiClient.getRulesList()

    override fun getNoticeDescription(notice_id: String): Single<NoticeDescriptionModel> = apiClient.getNoticeDescription(notice_id)

    override fun getRulesDescription(rule_id: String): Single<NoticeDescriptionModel> = apiClient.getRulesDescription(rule_id)
}