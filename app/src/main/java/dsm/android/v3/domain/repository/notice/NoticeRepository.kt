package dsm.android.v3.domain.repository.notice

import dsm.android.v3.domain.entity.NoticeListModel
import dsm.android.v3.domain.entity.RulesModel
import dsm.android.v3.presentation.model.NoticeDescriptionModel
import io.reactivex.Single

interface NoticeRepository {
    fun getNoticeList(): Single<NoticeListModel>

    fun getRulesList(): Single<RulesModel>

    fun getNoticeDescription(notice_id: String): Single<NoticeDescriptionModel>

    fun getRulesDescription(rule_id: String): Single<NoticeDescriptionModel>
}