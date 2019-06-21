package dsm.android.v3.domain.entity

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class MyPageInfoModel(
    @SerializedName("badPoint")
    var badPoint: Int,
    @SerializedName("goodPoint")
    var goodPoint: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("number")
    var number: Int,
    @SerializedName("advice")
    var advice: String,
    @SerializedName("penaltyLevel")
    var penaltyLevel: Int,
    @SerializedName("penaltyStatus")
    var penaltyStatus: Boolean
)