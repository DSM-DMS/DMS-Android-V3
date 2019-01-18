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
    val siballom = MutableLiveData<String>().apply { value = "Sibal" }
    val dateMerger = Transformations.map(pageStatusLiveData) {
        val date = Date(System.currentTimeMillis())
        SimpleDateFormat("yyyy년 mm월 dd일").format(date)
    }

    fun nextIndex() {
        Log.d("MealFragmentViewModel","nextIndex() called")
        pageStatusLiveData.value = pageStatusLiveData.value!! + 1
    }

    fun previousIndex() {
        Log.d("MealFragmentViewModel","previousIndex() called")
        pageStatusLiveData.value = pageStatusLiveData.value!! + 1
    }
}