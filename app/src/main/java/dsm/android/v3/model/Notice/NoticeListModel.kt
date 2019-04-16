package dsm.android.v3.model.Notice

import com.google.gson.annotations.SerializedName

data class NoticeListModel(
    @SerializedName("list", alternate = ["noticeListLiveData", "ruleList"])
    val list: ArrayList<NoticeModel>
)

data class RulesModel(val ruleList: ArrayList<NoticeModel>)

data class NoticeModel(
    @SerializedName("id", alternate = ["ruleId", "noticeId"]) val id: Int,
    val postDate: String,
    val title: String
)