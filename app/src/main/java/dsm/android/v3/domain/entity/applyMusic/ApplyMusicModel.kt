package dsm.android.v3.domain.entity.applyMusic

import com.google.gson.annotations.SerializedName

data class ApplyMusicModel(
    @SerializedName("mon")
    val mon: ArrayList<ApplyMusicDetailModel> = ArrayList(),

    @SerializedName("tue")
    val tue: ArrayList<ApplyMusicDetailModel> = ArrayList(),

    @SerializedName("wed")
    val wed: ArrayList<ApplyMusicDetailModel> = ArrayList(),

    @SerializedName("thu")
    val thu: ArrayList<ApplyMusicDetailModel> = ArrayList(),

    @SerializedName("fri")
    val fri: ArrayList<ApplyMusicDetailModel> = ArrayList()
)