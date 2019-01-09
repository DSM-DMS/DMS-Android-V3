package dsm.android.v3.ui.main.meal

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.databinding.BindingMethod
import android.support.v4.view.ViewPager
import android.util.Log
import org.jetbrains.anko.support.v4.onPageChangeListener

object MealBindingAdapter {

    @JvmStatic
    @BindingAdapter("pageChange")
    fun ViewPager.pageChange(data: MutableLiveData<Int>){
        onPageChangeListener {
            onPageSelected {
                Log.d("MealBindingAdapter", "Page is $it")
                data.value = it
            }
        }
    }
}