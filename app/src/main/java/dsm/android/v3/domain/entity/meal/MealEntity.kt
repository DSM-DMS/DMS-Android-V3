package dsm.android.v3.domain.entity.meal

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class MealEntity(
    @PrimaryKey
    val date: String,

    val breakfast: String,

    val lunch: String,

    val dinner: String
)