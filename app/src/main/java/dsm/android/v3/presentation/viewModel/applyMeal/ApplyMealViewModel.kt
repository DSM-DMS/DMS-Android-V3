package dsm.android.v3.presentation.viewModel.applyMeal

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import dsm.android.v3.util.BaseViewModel
import dsm.android.v3.util.LifecycleCallback

class ApplyMealViewModel:BaseViewModel(),LifecycleCallback {
    val status = MutableLiveData<Int>().apply { value = 0 }

    override fun apply(event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_START->{

            }
        }
    }

    private fun getStatus(){

    }
}