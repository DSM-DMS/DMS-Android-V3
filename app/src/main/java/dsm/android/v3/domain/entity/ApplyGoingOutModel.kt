package dsm.android.v3.domain.entity

import com.google.gson.annotations.SerializedName

data class ApplyGoingOutModel(
    @SerializedName("saturday")
    var saturdayList: ArrayList<ApplyGoingDataModel>,

    @SerializedName("sunday")
    var sundayList: ArrayList<ApplyGoingDataModel>,

    @SerializedName("workday")
    var workdayList: ArrayList<ApplyGoingDataModel>
) {
    data class ApplyGoingDataModel(
        @SerializedName("date")
        var date: String,

        @SerializedName("id")
        var id: Int,

        @SerializedName("reason")
        var reason: String
    )
}