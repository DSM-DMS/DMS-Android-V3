package dsm.android.v3.domain.entity.applyMusic

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ApplyMusicDetailModel(
    @PrimaryKey
    @SerializedName("id")
    val id: String = "",

    @SerializedName("applyDate")
    val applyDate: String = "",

    @SerializedName("musicName")
    val musicName: String = "",

    @SerializedName("singer")
    val singer: String = "",

    @SerializedName("studentId")
    val studentId: String = "",

    @SerializedName("studentName")
    val studentName: String = "",

    @ColumnInfo(name = "week")
    var week: Int
)