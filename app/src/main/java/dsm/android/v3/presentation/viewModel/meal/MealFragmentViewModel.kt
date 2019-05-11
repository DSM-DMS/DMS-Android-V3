package dsm.android.v3.presentation.viewModel.meal

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import dsm.android.v3.domain.repository.meal.MealRepository
import dsm.android.v3.util.BaseViewModel
import java.text.SimpleDateFormat
import java.util.*

class MealFragmentViewModel : BaseViewModel() {

    val meals = MutableLiveData<ArrayList<String>>()
    val pageStatusLiveData = MutableLiveData<Int>().apply { postValue(3) }

    val dateLiveData = Transformations.map(pageStatusLiveData) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, it - 3)
        SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(calendar.time)
    }

    val weekLiveData = Transformations.map(pageStatusLiveData) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, it - 3)
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

        calender.add(Calendar.DAY_OF_YEAR, -3)
        val meal = arrayListOf<String>().apply {
            repeat((0..10).count()) {
                this.add(dataFormat.format(calender.time))
                calender.add(Calendar.DAY_OF_YEAR, 1)
            }
        }
        meals.value = meal
    }
}