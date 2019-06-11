package dsm.android.v3.domain.entity.rules

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class RulesEntity (
    @PrimaryKey
    @SerializedName("id")
    val id : Int,

    @SerializedName("postDate")
    val postDate : String,

    @SerializedName("title")
    val title : String
)