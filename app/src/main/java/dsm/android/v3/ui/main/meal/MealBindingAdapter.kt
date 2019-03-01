@file:JvmName("MealBindingAdapter")

package dsm.android.v3.ui.main.meal

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.databinding.BindingMethod
import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import android.support.v4.view.ViewPager
import android.util.Log
import org.jetbrains.anko.support.v4.onPageChangeListener

@BindingAdapter("pageChange")
fun ViewPager.setPageChange(data: MutableLiveData<Int>) {
    Log.d("MealBindingAdapter", "Page is $currentItem")
    if (currentItem != data.value)
        currentItem = data.value!!
}

@InverseBindingAdapter(attribute = "pageChange")
fun ViewPager.getPageChange(): Int {
    return currentItem
}


@BindingAdapter("pageChangeAttrChanged")
fun ViewPager.setPageChangeListener(listener: InverseBindingListener) {
    onPageChangeListener {
        onPageSelected {
            listener.onChange()
        }
    }
}