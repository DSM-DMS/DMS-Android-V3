package dsm.android.v3.domain.entity

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import dsm.android.v3.presentation.model.NoticeModel

@Entity
data class NoticeListModel (@SerializedName("noticeList") var noticeList : ArrayList<NoticeModel>)