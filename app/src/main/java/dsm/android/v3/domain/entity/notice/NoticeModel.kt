package dsm.android.v3.domain.entity.notice

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class NoticeModel (
    @PrimaryKey
    @SerializedName("id")
    val id : Int,

    @SerializedName("postDate")
    val postDate : String,

    @SerializedName("title")
    val title : String
)