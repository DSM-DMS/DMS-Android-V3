package dsm.android.v3.model.notice

import com.google.gson.annotations.SerializedName

data class NoticeListModel(
    @SerializedName("list", alternate = ["noticeList", "ruleList"])
    val list: ArrayList<NoticeModel> = arrayListOf()
)

data class RulesModel(val ruleList: ArrayList<NoticeModel>)

data class NoticeModel(
    @SerializedName("id", alternate = ["ruleId", "noticeId"]) val id: Int,
    var postDate: String,
    val title: String
)