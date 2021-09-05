package dsm.android.v3.presentation.viewModel.applyMeal

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.view.View
import dsm.android.v3.domain.repository.applyMeal.ApplyMealRepository
import dsm.android.v3.util.BaseViewModel
import dsm.android.v3.util.SingleLiveEvent

class ApplyMealViewModel(private val applyMealRepository: ApplyMealRepository):BaseViewModel() {
    val status = MutableLiveData<Int>().apply { value = 0 }

    val toast = SingleLiveEvent<String>()

    val changeColorLiveEvent = SingleLiveEvent<Any>()
    val originalColorLiveEvent = SingleLiveEvent<Any>()

    val selectedView = MutableLiveData<View>()

    @SuppressLint("CheckResult")
    fun getStatus(){
        applyMealRepository.getStatus().subscribe{response->
            if(response.isSuccessful){
                status.value = (response.body()!!.value)-1
                setCardViewData()
            }
        }
    }

    @SuppressLint("CheckResult")
    fun postStatus(){
        if(status.value !=0){
            val status = (status.value?:0)+1
            applyMealRepository.applyStatus(hashMapOf("value" to status)).subscribe ({ response->
                when(response.code()){
                    200 -> setCardViewData()
                    403 -> toast.value = "권한이 없습니다."
                }
            },{
                toast.value = "네트워크 상태를 확인해주세요."
            })
        }
    }

    private fun setCardViewData(){
        selectedView.value?.let { originalColorLiveEvent.call() }
        changeColorLiveEvent.call()
    }

}