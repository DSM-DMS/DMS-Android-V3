@file:JvmName("ApplyGoingBindingAdapter")

package dsm.android.v3.ui.applyGoingOut.applyGoing

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.support.v4.view.ViewPager
import dsm.android.v3.model.ApplyPagerModel

@BindingAdapter("applyGoingPagerItems")
fun ViewPager.bindApplyGoingPagerItems(applyPagerItems: MutableLiveData<ArrayList<ApplyPagerModel>>) {
    val adapter = adapter as ApplyGoingPageAdapter
    adapter.setItem(applyPagerItems)
}