package dsm.android.v3.domain.entity.applyStaying

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ApplyStayingModel(
    @PrimaryKey
    @SerializedName("value")
    var value: Int)