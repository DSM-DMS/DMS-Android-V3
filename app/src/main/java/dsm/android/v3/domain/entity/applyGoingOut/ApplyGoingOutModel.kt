package dsm.android.v3.domain.entity.applyGoingOut

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ApplyGoingOutModel(
    @SerializedName("saturday")
    var saturdayList: ArrayList<ApplyGoingDataModel>,

    @SerializedName("sunday")
    var sundayList: ArrayList<ApplyGoingDataModel>,

    @SerializedName("workday")
    var workdayList: ArrayList<ApplyGoingDataModel>
) {
    @Entity
    data class ApplyGoingDataModel(
        @PrimaryKey
        @SerializedName("id")
        val id: Int,

        @SerializedName("date")
        val date: String,

        @SerializedName("reason")
        val reason: String,

        @ColumnInfo(name = "week")
        val week: Int
    )
}