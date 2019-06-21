package dsm.android.v3.domain.entity

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import dsm.android.v3.presentation.model.NoticeModel

data class NoticeListModel(
    @SerializedName("list", alternate = ["noticeList", "ruleList"])
    val list: ArrayList<NoticeModel> = arrayListOf()
)

data class NoticeModel(
    @SerializedName("id", alternate = ["ruleId", "noticeId"]) val id: Int,
    var postDate: String,
    val title: String
)
