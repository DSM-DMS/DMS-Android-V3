@file:JvmName("ApplyMusicBindingAdapter")

package dsm.android.v3.ui.applyMusic

import android.arch.lifecycle.LiveData
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import dsm.android.v3.adapter.ApplyMusicAdapter
import dsm.android.v3.model.ApplyMusicDetailModel

@BindingAdapter("musicData")
fun RecyclerView.setMusicData(data: LiveData<ArrayList<ApplyMusicDetailModel>>) {
    val musicAdapter: ApplyMusicAdapter? = adapter as ApplyMusicAdapter
    data.value?.let { musicAdapter?.item = it }
}