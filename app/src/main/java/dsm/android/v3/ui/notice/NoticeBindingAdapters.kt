@file:JvmName("NoticeBindingAdapter")

package dsm.android.v3.ui.notice

import android.arch.lifecycle.LiveData
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import dsm.android.v3.adapter.notice.NoticeAdapter
import dsm.android.v3.model.notice.NoticeListModel

@BindingAdapter("noticeData")
fun RecyclerView.setNoticeData(data: LiveData<NoticeListModel>) {
    val noticeAdapter: NoticeAdapter? = adapter as NoticeAdapter
    data.value?.let { noticeAdapter?.item = it }
}