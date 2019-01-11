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
    currentItem = data.value!!
}

@InverseBindingAdapter(attribute = "pageChange")
fun ViewPager.getPageChange(): Int{
    return currentItem
}


@BindingAdapter("pageChangeAttrChanged")
fun ViewPager.setPageChangeListener(listener: InverseBindingListener) {
    addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(p0: Int) {

        }

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

        }

        override fun onPageSelected(p0: Int) {
            listener.onChange()
        }

    })
//    onPageChangeListener {
//        onPageSelected {
//            listener.onChange()
//        }
//    }
}