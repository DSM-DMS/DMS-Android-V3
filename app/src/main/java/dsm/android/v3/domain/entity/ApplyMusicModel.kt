package dsm.android.v3.domain.entity

import android.arch.persistence.room.Entity
import dsm.android.v3.presentation.model.ApplyMusicDetailModel

@Entity
data class ApplyMusicModel(
    val mon: ArrayList<ApplyMusicDetailModel> = ArrayList(),
    val tue: ArrayList<ApplyMusicDetailModel> = ArrayList(),
    val wed: ArrayList<ApplyMusicDetailModel> = ArrayList(),
    val thu: ArrayList<ApplyMusicDetailModel> = ArrayList(),
    val fri: ArrayList<ApplyMusicDetailModel> = ArrayList()
)