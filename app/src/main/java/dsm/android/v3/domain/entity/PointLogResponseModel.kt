package dsm.android.v3.domain.entity

import com.google.gson.annotations.SerializedName
import dsm.android.v3.presentation.model.PointLogItemModel

data class PointLogResponseModel(
    @SerializedName("point_history")
    val pointHistory: ArrayList<PointLogItemModel>
)