package dsm.android.v3.model

import com.google.gson.annotations.SerializedName

class NoticeListModel (@SerializedName("noticeList") var notice : ArrayList<NoticeModel>)
class NoticeModel (var id : Int, var postDate : String, var title : String)