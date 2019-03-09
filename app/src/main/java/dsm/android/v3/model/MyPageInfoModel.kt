package dsm.android.v3.model

import com.google.gson.annotations.SerializedName

data class MyPageInfoModel(
    @SerializedName("badPoint")
    var badPoint: Int,
    @SerializedName("goodPoint")
    var goodPoint: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("number")
    var number: Int,
    @SerializedName("penaltyLevel")
    var penaltyLevel: Int,
    @SerializedName("penaltyStatus")
    var penaltyStatus: Boolean
)