package dsm.android.v3.presentation.viewModel.applyMeal

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.widget.Toast
import dsm.android.v3.domain.repository.applyMeal.ApplyMealRepository
import dsm.android.v3.util.BaseViewModel
import dsm.android.v3.util.LifecycleCallback
import dsm.android.v3.util.SingleLiveEvent

class ApplyMealViewModel(val applyMealRepository: ApplyMealRepository):BaseViewModel() {
    val status = MutableLiveData<Int>().apply { value = 0 }

    val toast = SingleLiveEvent<String>()

    @SuppressLint("CheckResult")
    fun getStatus(){
        applyMealRepository.getStatus().subscribe{response->
            if(response.isSuccessful){
                status.value = response.body()!!.status
            }
        }
    }

    @SuppressLint("CheckResult")
    fun postStatus(){
        if(status.value !=0){
            applyMealRepository.applyStatus(hashMapOf("status" to status.value)).subscribe ({ response->
                when(response.code()){
                    200 -> toast.value = "주말급식신청을 완료했습니다."
                    403 -> toast.value = "권한이 없습니다."
                }
            },{
                toast.value = "네트워크 상태를 확인해주세요."
            })
        }
    }

}