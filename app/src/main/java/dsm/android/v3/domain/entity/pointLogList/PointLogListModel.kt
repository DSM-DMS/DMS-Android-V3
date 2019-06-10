package dsm.android.v3.domain.entity.pointLogList

import com.google.gson.annotations.SerializedName

data class PointLogListModel(
    @SerializedName("point_history")
    val pointHistory: ArrayList<PointLogItemModel>
)