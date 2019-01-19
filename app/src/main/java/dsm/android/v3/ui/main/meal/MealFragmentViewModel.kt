package dsm.android.v3.ui.main.meal

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class MealFragmentViewModel : ViewModel() {

    val pageStatusLiveData = MutableLiveData<Int>().apply { value = 0 }

    val dateMerger = Transformations.map(pageStatusLiveData) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, it)
        SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(calendar.time)
    }

    val weekMerger = Transformations.map(pageStatusLiveData) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, it)
        SimpleDateFormat("EEEE", Locale.KOREA).format(calendar.time)
    }

    fun nextIndex() {
        pageStatusLiveData.value = pageStatusLiveData.value!! + 1
    }

    fun previousIndex() {
        pageStatusLiveData.value = pageStatusLiveData.value!! - 1
    }
}