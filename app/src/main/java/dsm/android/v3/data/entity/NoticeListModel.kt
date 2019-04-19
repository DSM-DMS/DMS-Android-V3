package dsm.android.v3.data.entity

import com.google.gson.annotations.SerializedName

class NoticeListModel (@SerializedName("noticeList") var noticeList : ArrayList<NoticeModel>)

class RulesModel(var ruleList : ArrayList<NoticeModel>)

class NoticeModel (var id : Int, var postDate : String, var title : String)