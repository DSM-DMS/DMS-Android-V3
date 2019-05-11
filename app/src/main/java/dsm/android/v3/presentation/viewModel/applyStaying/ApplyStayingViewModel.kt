package dsm.android.v3.presentation.viewModel.applyStaying

import android.arch.lifecycle.MutableLiveData
import android.view.View
import dsm.android.v3.domain.repository.applyStaying.ApplyStayingRepository
import dsm.android.v3.util.BaseViewModel
import dsm.android.v3.util.SingleLiveEvent

class ApplyStayingViewModel(val applyStayingRepository: ApplyStayingRepository): BaseViewModel(){

    val selectedView = MutableLiveData<View>()
    val toastLiveData = MutableLiveData<String>()
    val originalColorLiveEvent = SingleLiveEvent<Any>()
    val changeColorLiveEvent = SingleLiveEvent<Any>()
    val pageStatusLiveData = MutableLiveData<Int>().apply { value = 0 }

    init { getStayInfo() }

    fun applyBtnClick() {
        add(applyStayingRepository.applyStay(hashMapOf("value" to pageStatusLiveData.value!! + 1))
            .subscribe({ response ->
                toastLiveData.value = (
                    when(response.code()){
                        201 -> "잔류신청에 성공했습니다."
                        403 -> "잔류신청 권한이 없습니다."
                        409 -> "잔류신청 가능시간이 아닙니다."
                        else -> "오류코드: ${response.code()}"
                    })
                getStayInfo()
            }, {
                toastLiveData.value = "오류가 발생했습니다."
            }))
    }

    fun getStayInfo(){
        add(applyStayingRepository.getStayInfo()
            .subscribe({ response ->
                when(response.code()){
                    200 -> setStayingData(response.body()!!.value - 1)
                    403 -> toastLiveData.value = "잔류신청 조회 권한이 없습니다."
                    else -> toastLiveData.value = "오류코드: ${response.code()}"
                }
            }, {
                toastLiveData.value = "오류가 발생했습니다."
            }))
    }

    fun setStayingData(currentItem: Int){
        selectedView.value?.let { originalColorLiveEvent.call() }
        pageStatusLiveData.value = currentItem
        changeColorLiveEvent.call()
    }
}