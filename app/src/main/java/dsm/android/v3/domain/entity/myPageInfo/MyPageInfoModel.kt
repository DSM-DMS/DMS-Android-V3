package dsm.android.v3.domain.entity.myPageInfo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MyPageInfoModel(
    @PrimaryKey
    @SerializedName("name")
    var name: String,

    @SerializedName("badPoint")
    var badPoint: Int,

    @SerializedName("goodPoint")
    var goodPoint: Int,

    @SerializedName("number")
    var number: Int,

    @SerializedName("advice")
    var advice: String,

    @SerializedName("penaltyLevel")
    var penaltyLevel: Int,

    @SerializedName("penaltyStatus")
    var penaltyStatus: Boolean
)