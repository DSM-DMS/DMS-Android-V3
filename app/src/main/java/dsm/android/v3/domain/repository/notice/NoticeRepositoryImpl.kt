package dsm.android.v3.domain.repository.notice

import dsm.android.v3.data.local.dao.NoticeDao
import dsm.android.v3.data.local.dao.RulesDao
import dsm.android.v3.data.remote.ApiClient
import dsm.android.v3.domain.entity.notice.NoticeListModel
import dsm.android.v3.domain.entity.rules.RulesModel
import dsm.android.v3.presentation.model.NoticeDescriptionModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

class NoticeRepositoryImpl(
    val apiClient: ApiClient,
    val noticeDao: NoticeDao,
    val rulesDao: RulesDao): NoticeRepository {
    override fun getNoticeList(): Single<Response<NoticeListModel>> = apiClient.getNoticeList()

    override fun getRulesList(): Single<Response<RulesModel>> = apiClient.getRulesList()

    override fun getNoticeDescription(notice_id: String): Single<Response<NoticeDescriptionModel>> = apiClient.getNoticeDescription(notice_id)

    override fun getRulesDescription(rule_id: String): Single<Response<NoticeDescriptionModel>> = apiClient.getRulesDescription(rule_id)

    override fun loadNoticeList(): Flowable<NoticeListModel> = noticeDao.getAll()

    override fun loadRulesList(): Flowable<RulesModel> = rulesDao.getAll()

    override fun saveNoticeList(noticeListModel: NoticeListModel) = noticeDao.insertAll(noticeListModel)

    override fun saveRulesList(rulesModel: RulesModel) = rulesDao.insertAll(rulesModel)
}