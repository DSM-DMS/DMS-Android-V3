package dsm.android.v3.model

data class ApplyMusicModel(
    val mon: ArrayList<ApplyMusicDetailModel> = ArrayList(),
    val tue: ArrayList<ApplyMusicDetailModel> = ArrayList(),
    val wed: ArrayList<ApplyMusicDetailModel> = ArrayList(),
    val thu: ArrayList<ApplyMusicDetailModel> = ArrayList(),
    val fri: ArrayList<ApplyMusicDetailModel> = ArrayList()
)

data class ApplyMusicDetailModel(
    val applyDate: String = "",
    val id: String = "",
    val musicName: String = "",
    val singer: String = "",
    val studentId: String = "",
    val studentName: String = ""
)