package dsm.android.v3.domain.entity.meal

import com.google.gson.annotations.SerializedName

data class MealModel(
    @SerializedName("breakfast")
    val breakfast: ArrayList<String>,

    @SerializedName("lunch")
    val lunch: ArrayList<String>,

    @SerializedName("dinner")
    val dinner: ArrayList<String>
)