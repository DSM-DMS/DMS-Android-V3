package dsm.android.v3.domain.entity.rules

import com.google.gson.annotations.SerializedName

data class RulesModel(
    @SerializedName("ruleList")
    var ruleList : ArrayList<RulesEntity>
)