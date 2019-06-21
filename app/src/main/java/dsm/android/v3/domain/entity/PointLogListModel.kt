package dsm.android.v3.domain.entity

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import dsm.android.v3.presentation.model.PointLogItemModel

@Entity
data class PointLogListModel(
    @SerializedName("point_history")
    val pointHistory: ArrayList<PointLogItemModel>
)