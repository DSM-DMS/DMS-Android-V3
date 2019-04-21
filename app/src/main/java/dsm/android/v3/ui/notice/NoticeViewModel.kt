package dsm.android.v3.ui.notice

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.util.Log
import dsm.android.v3.connecter.Connecter
import dsm.android.v3.model.notice.NoticeDescriptionModel
import dsm.android.v3.model.notice.NoticeListModel
import dsm.android.v3.util.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticeViewModel() : ViewModel() {
    val noticeTypeLiveData = MutableLiveData<Int>() // 0: 공지사항, 1: 기숙사 규칙
    val titleLiveData = Transformations.map(noticeTypeLiveData) {
        when(it){
            0 -> "공지사항"
            else -> "기숙사 규칙"
        }
    }
    val descriptionLiveData = Transformations.map(noticeTypeLiveData) {
        when(it){
            0 -> "사감부에서 게시한 공지사항을 열람합니다."
            else -> "사감부에서 게시한 기숙사 규칙을 열람합니다."
        }
    }
    val noticeIdLiveData = MutableLiveData<Int>()
    val noticeListLiveData = MutableLiveData<NoticeListModel>()
    val noticeDescriptionLiveData = MutableLiveData<NoticeDescriptionModel>()

    val getNoticeListLiveEvent = SingleLiveEvent<Any>()
    val getDescriptionLiveEvent = SingleLiveEvent<Any>()

    val finishNoticeListLiveEvent = SingleLiveEvent<Any>()
    val closeDescriptionLiveEvent = SingleLiveEvent<Any>()

    fun getNoticeList() {
        when (noticeTypeLiveData.value) {
            0 -> Connecter.api.getNoticeList()
            else -> Connecter.api.getRulesList()
        }.enqueue(object : Callback<NoticeListModel> {
            override fun onResponse(call: Call<NoticeListModel>, response: Response<NoticeListModel>) {
                response.body()?.let {
                    it.list.forEach { notice ->
                        notice.postDate = notice.postDate.substring(0, 10)
                    }
                    noticeListLiveData.value = it
                }
            }

            override fun onFailure(call: Call<NoticeListModel>, t: Throwable) {
            }

        })
    }

    fun getDescription() {
        when (noticeTypeLiveData.value) {
            0 -> Connecter.api.getNoticeDescription(noticeIdLiveData.value.toString())
            else -> Connecter.api.getRulesDescription(noticeIdLiveData.value.toString())
        }.enqueue(object : Callback<NoticeDescriptionModel> {
            override fun onResponse(
                call: Call<NoticeDescriptionModel>,
                response: Response<NoticeDescriptionModel>
            ) {
                response.body()?.let {
                    noticeDescriptionLiveData.value = it
                }
            }

            override fun onFailure(call: Call<NoticeDescriptionModel>, t: Throwable) {

            }

        })
    }

    fun onNoticeClick(type: Int) {
        noticeTypeLiveData.value = type
        getNoticeList()
        noticeListLiveData.value?.list?.clear()
        getNoticeListLiveEvent.call()
    }

    fun onListClicked(index: Int) {
        noticeIdLiveData.value = index
        getDescription()
        getDescriptionLiveEvent.call()
    }

    fun finishNoticeListCall() {
        finishNoticeListLiveEvent.call()
    }

    fun closeEventCall() {
        closeDescriptionLiveEvent.call()
    }

}