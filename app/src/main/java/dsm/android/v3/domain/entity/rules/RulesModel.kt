package dsm.android.v3.domain.entity.rules

import com.google.gson.annotations.SerializedName
import dsm.android.v3.domain.entity.notice.NoticeModel

data class RulesModel(
    @SerializedName("ruleList")
    var ruleList : ArrayList<NoticeModel>
)