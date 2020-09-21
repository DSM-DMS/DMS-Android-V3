package dsm.android.v3.presentation.viewModel.notice

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import dsm.android.v3.domain.entity.NoticeListModel
import dsm.android.v3.domain.repository.notice.NoticeRepository
import dsm.android.v3.domain.entity.NoticeDescriptionModel
import dsm.android.v3.util.BaseViewModel
import dsm.android.v3.util.SingleLiveEvent

class NoticeViewModel(val noticeRepository: NoticeRepository) : BaseViewModel() {
    val noticeTypeLiveData = MutableLiveData<Int>() // 0: 공지사항, 1: 기숙사 규칙
    val titleLiveData = Transformations.map(noticeTypeLiveData) {
        when (it) {
            0 -> "공지사항"
            else -> "기숙사 규칙"
        }
    }
    val descriptionLiveData = Transformations.map(noticeTypeLiveData) {
        when (it) {
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
        add(
            when (noticeTypeLiveData.value) {
                0 -> noticeRepository.getNoticeList()
                else -> noticeRepository.getRulesList()
            }.subscribe({ response ->
                response.body()?.list?.forEach {
                    it.postDate = it.postDate.substring(0, 10)
                }
                noticeListLiveData.value = response.body()
            }, {})
        )
    }

    fun getDescription() {
        add(
            when (noticeTypeLiveData.value) {
                0 -> noticeRepository.getNoticeDescription(noticeIdLiveData.value.toString())
                else -> noticeRepository.getRulesDescription(noticeIdLiveData.value.toString())
            }.subscribe({ response ->
                noticeDescriptionLiveData.value = response.body()
            }, {})
        )
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