package dsm.android.v3.model

import com.google.gson.annotations.SerializedName

data class ApplyGoingModel(
    @SerializedName("saturday")
    var saturdayList: ArrayList<ApplyGoingDataModel>,

    @SerializedName("sunday")
    var sundayList: ArrayList<ApplyGoingDataModel>,

    @SerializedName("workday")
    var workdayList: ArrayList<ApplyGoingDataModel>
) {
    data class ApplyGoingDataModel(
        @SerializedName("go_out_date")
        var goOutDate: String,

        @SerializedName("id")
        var id: Int,

        @SerializedName("reason")
        var reason: String
    )
}