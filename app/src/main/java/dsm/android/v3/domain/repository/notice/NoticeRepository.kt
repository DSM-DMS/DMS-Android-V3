package dsm.android.v3.domain.repository.notice

import dsm.android.v3.domain.entity.NoticeDescriptionModel
import dsm.android.v3.domain.entity.NoticeListModel
import io.reactivex.Single
import retrofit2.Response

interface NoticeRepository {
    fun getNoticeList(): Single<Response<NoticeListModel>>

    fun getRulesList(): Single<Response<NoticeListModel>>

    fun getNoticeDescription(notice_id: String): Single<Response<NoticeDescriptionModel>>

    fun getRulesDescription(rule_id: String): Single<Response<NoticeDescriptionModel>>
}