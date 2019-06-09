package dsm.android.v3.domain.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class ApplyExtensionStudyModel(
    @SerializedName("map")
    val map: ArrayList<ArrayList<Any>>)