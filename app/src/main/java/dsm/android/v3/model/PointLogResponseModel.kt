package dsm.android.v3.model

import com.google.gson.annotations.SerializedName

data class PointLogResponseModel(
    @SerializedName("point_history")
    val pointHistory: ArrayList<PointLogItemModel>
)