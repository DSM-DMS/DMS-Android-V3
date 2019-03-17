package dsm.android.v3.ui.main.meal

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.util.Log
import dsm.android.v3.connecter.Connecter
import dsm.android.v3.model.MealModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MealFragmentViewModel : ViewModel() {

    val meals = MutableLiveData<ArrayList<String>>()

    val pageStatusLiveData = MutableLiveData<Int>().apply { value = 0 }

    val dateLiveData = Transformations.map(pageStatusLiveData) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, it)
        SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(calendar.time)
    }

    val weekLiveData = Transformations.map(pageStatusLiveData) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, it)
        SimpleDateFormat("EEEE", Locale.KOREA).format(calendar.time)
    }

    fun nextIndex() {
        if (pageStatusLiveData.value!! < meals.value?.size!! - 1)
            pageStatusLiveData.value = pageStatusLiveData.value!! + 1
    }

    fun previousIndex() {
        if (pageStatusLiveData.value!! > 0)
            pageStatusLiveData.value = pageStatusLiveData.value!! - 1
    }

    fun getMeal() {
        val calender = Calendar.getInstance()
        val dataFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
//        val list = arrayListOf("수수밥", "맑은무채국", "비엔나푸실리볶음", "조기구이", "사과", "배추겉절이")
        val meal = arrayListOf<String>().apply {
            (0..7).forEach {
                this.add(dataFormat.format(calender.time))
                calender.add(Calendar.DAY_OF_YEAR, 1)
            }
        }
        meals.value = meal
    }
}