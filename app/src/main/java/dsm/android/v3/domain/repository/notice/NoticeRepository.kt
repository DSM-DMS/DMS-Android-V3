package dsm.android.v3.domain.repository.notice

import dsm.android.v3.domain.entity.NoticeListModel
import dsm.android.v3.domain.entity.RulesModel
import dsm.android.v3.presentation.model.NoticeDescriptionModel
import io.reactivex.Single
import retrofit2.Response

interface NoticeRepository {
    fun getNoticeList(): Single<Response<NoticeListModel>>

    fun getRulesList(): Single<Response<RulesModel>>

    fun getNoticeDescription(notice_id: String): Single<Response<NoticeDescriptionModel>>

    fun getRulesDescription(rule_id: String): Single<Response<NoticeDescriptionModel>>
}