@file:JvmName("ApplyGoingLogBindingAdapter")

package dsm.android.v3.ui.bindingAdapter

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import dsm.android.v3.domain.entity.applyGoingOut.ApplyGoingOutModel
import dsm.android.v3.presentation.model.ApplyPagerModel
import dsm.android.v3.ui.adapter.ApplyGoingLogAdapter
import dsm.android.v3.ui.adapter.ApplyPageAdapter

@BindingAdapter("logItems")
fun RecyclerView.bindLogItems(logItems: MutableLiveData<ArrayList<ApplyGoingOutModel.ApplyGoingDataModel>>) {
    val adapter = adapter as ApplyGoingLogAdapter
    adapter.setItem(logItems)
}

@BindingAdapter("applyPagerItems")
fun ViewPager.bindApplyPagerItems(applyPagerItems: MutableLiveData<ArrayList<ApplyPagerModel>>) {
    val adapter = adapter as ApplyPageAdapter
    adapter.setItem(applyPagerItems)
}