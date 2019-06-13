package dsm.android.v3.domain.entity

import android.arch.persistence.room.Entity

@Entity
class MealModel(val breakfast: ArrayList<String>, val lunch: ArrayList<String>, val dinner: ArrayList<String>)