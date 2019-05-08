@file:JvmName("ApplyGoingLogBindingAdapter")

package dsm.android.v3.ui.applyGoingOut.applyGoingLog

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import dsm.android.v3.adapter.ApplyGoingLogAdapter
import dsm.android.v3.model.ApplyGoingModel

@BindingAdapter("logItems")
fun RecyclerView.bindLogItems(logItems: MutableLiveData<ArrayList<ApplyGoingModel.ApplyGoingDataModel>>) {
    val adapter = adapter as ApplyGoingLogAdapter
    adapter.setItem(logItems)
}