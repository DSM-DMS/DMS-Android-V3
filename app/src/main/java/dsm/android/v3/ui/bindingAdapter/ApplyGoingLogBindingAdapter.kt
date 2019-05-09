@file:JvmName("ApplyGoingLogBindingAdapter")

package dsm.android.v3.ui.bindingAdapter

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import dsm.android.v3.domain.entity.ApplyGoingOutModel
import dsm.android.v3.ui.adapter.ApplyGoingLogAdapter

@BindingAdapter("logItems")
fun RecyclerView.bindLogItems(logItems: MutableLiveData<ArrayList<ApplyGoingOutModel.ApplyGoingDataModel>>) {
    val adapter = adapter as ApplyGoingLogAdapter
    adapter.setItem(logItems)
}