package dsm.android.v3.domain.entity.pointLogList

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PointLogItemModel(
    @PrimaryKey
    val id: String,

    @SerializedName("reason")
    val reason: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("point")
    val point: Int,

    @SerializedName("pointType")
    val pointType: Boolean)