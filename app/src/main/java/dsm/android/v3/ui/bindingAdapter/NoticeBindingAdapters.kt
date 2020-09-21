@file:JvmName("NoticeBindingAdapter")

package dsm.android.v3.ui.bindingAdapter

import android.arch.lifecycle.LiveData
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import dsm.android.v3.ui.adapter.NoticeAdapter
import dsm.android.v3.domain.entity.NoticeListModel

@BindingAdapter("noticeData")
fun RecyclerView.setNoticeData(data: LiveData<NoticeListModel>) {
    val noticeAdapter: NoticeAdapter? = adapter as NoticeAdapter
    data.value?.let { noticeAdapter?.item = it }
}