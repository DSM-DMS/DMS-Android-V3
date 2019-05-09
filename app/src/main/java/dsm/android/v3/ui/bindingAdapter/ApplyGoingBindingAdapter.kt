@file:JvmName("ApplyGoingBindingAdapter")

package dsm.android.v3.ui.bindingAdapter

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.support.v4.view.ViewPager
import dsm.android.v3.presentation.model.ApplyPagerModel
import dsm.android.v3.ui.adapter.ApplyPageAdapter

@BindingAdapter("applyPagerItems")
fun ViewPager.bindApplyPagerItems(applyPagerItems: MutableLiveData<ArrayList<ApplyPagerModel>>) {
    val adapter = adapter as ApplyPageAdapter
    adapter.setItem(applyPagerItems)
}