package dsm.android.v3.ui.main.meal

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MealFragmentViewModel : ViewModel() {
    val pageStatusLiveData = MutableLiveData<Int>().apply { value = 0 }
}