@file:JvmName("ApplyMusicBindingAdapter")

package dsm.android.v3.ui.applyMusic

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import dsm.android.v3.adapter.ApplyMusicAdapter
import dsm.android.v3.model.ApplyMusicDetailModel
import dsm.android.v3.model.ApplyPagerModel
import dsm.android.v3.util.ApplyMusicViewPager

@BindingAdapter("musicData")
fun RecyclerView.setMusicData(data: LiveData<ArrayList<ApplyMusicDetailModel>>) {
    val musicAdapter: ApplyMusicAdapter? = adapter as ApplyMusicAdapter
    data.value?.let { musicAdapter?.item = it }
}

@BindingAdapter("applyMusicPagerItems")
fun ApplyMusicViewPager.bindApplyMusicPagerItems(applyPagerItems: MutableLiveData<ArrayList<ApplyPagerModel>>) {
    val adapter = adapter as ApplyMusicPageAdapter
    adapter.setItem(applyPagerItems)
}