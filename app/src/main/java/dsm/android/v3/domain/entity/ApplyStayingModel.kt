package dsm.android.v3.domain.entity

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class ApplyStayingModel(@SerializedName("value") var value: Int)