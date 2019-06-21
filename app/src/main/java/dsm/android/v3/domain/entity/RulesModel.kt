package dsm.android.v3.domain.entity

import android.arch.persistence.room.Entity
import dsm.android.v3.presentation.model.NoticeModel

@Entity
data class RulesModel(var ruleList : ArrayList<NoticeModel>)