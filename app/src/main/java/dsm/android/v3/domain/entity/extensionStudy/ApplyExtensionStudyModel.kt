package dsm.android.v3.domain.entity.extensionStudy

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ApplyExtensionStudyModel(
    @PrimaryKey
    val room: Int,

    @ColumnInfo(name = "map")
    @SerializedName("map")
    val map: ArrayList<ArrayList<Any>>
)