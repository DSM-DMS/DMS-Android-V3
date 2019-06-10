package dsm.android.v3.domain.entity.notice

import com.google.gson.annotations.SerializedName

data class NoticeListModel (
    @SerializedName("noticeList")
    val noticeList : ArrayList<NoticeModel>
)