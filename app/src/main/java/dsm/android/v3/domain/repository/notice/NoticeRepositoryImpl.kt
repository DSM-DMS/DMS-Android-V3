package dsm.android.v3.domain.repository.notice

import dsm.android.v3.data.local.dao.OfflineDao
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.notice.NoticeEntity
import dsm.android.v3.domain.entity.notice.NoticeListModel
import dsm.android.v3.domain.entity.rules.RulesEntity
import dsm.android.v3.domain.entity.rules.RulesModel
import dsm.android.v3.presentation.model.NoticeDescriptionModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

class NoticeRepositoryImpl(
    val apiClient: ApiClient,
    val dao: OfflineDao): NoticeRepository {
    override fun getNoticeList(): Single<Response<NoticeListModel>> = apiClient.getNoticeList()

    override fun getRulesList(): Single<Response<RulesModel>> = apiClient.getRulesList()

    override fun getNoticeDescription(notice_id: String): Single<Response<NoticeDescriptionModel>> = apiClient.getNoticeDescription(notice_id)

    override fun getRulesDescription(rule_id: String): Single<Response<NoticeDescriptionModel>> = apiClient.getRulesDescription(rule_id)

    override fun loadNoticeList(): Single<ArrayList<NoticeEntity>> = dao.getNotice()

    override fun loadRulesList(): Single<ArrayList<RulesEntity>> = dao.getRules()

    override fun saveNoticeList(noticeEntity: NoticeEntity) = dao.insertNotice(noticeEntity)

    override fun saveRulesList(rulesEntity: RulesEntity) = dao.insertRules(rulesEntity)
}