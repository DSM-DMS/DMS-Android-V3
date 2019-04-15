package dsm.android.v3.ui.applyStaying

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.view.View
import dsm.android.v3.connecter.api
import dsm.android.v3.model.ApplyStayingModel
import dsm.android.v3.util.SingleLiveEvent
import dsm.android.v3.util.getToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplyStayingViewModel(val app: Application): AndroidViewModel(app){

    val selectedView = MutableLiveData<View>()
    val toastLiveData = MutableLiveData<String>()
    val originalColorLiveEvent = SingleLiveEvent<Any>()
    val changeColorLiveEvent = SingleLiveEvent<Any>()
    val pageStatusLiveData = MutableLiveData<Int>().apply { value = 0 }

    init { getStayInfo() }

    fun applyBtnClick() {
        api.applyStay(getToken(app.baseContext), hashMapOf("value" to pageStatusLiveData.value!! + 1)).enqueue(object: Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                toastLiveData.value = (
                    when(response.code()){
                        201 -> "잔류신청에 성공했습니다."
                        403 -> "잔류신청 권한이 없습니다."
                        409 -> "잔류신청 가능시간이 아닙니다."
                        else -> "오류코드: ${response.code()}"
                })
                getStayInfo()
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                toastLiveData.value = "오류가 발생했습니다."
            }
        })
    }

    fun getStayInfo(){
        api.getStayInfo(getToken(app.baseContext)).enqueue(object: Callback<ApplyStayingModel> {
            override fun onResponse(call: Call<ApplyStayingModel>, response: Response<ApplyStayingModel>) {
                when(response.code()){
                    200 -> setStayingData(response.body()!!.value - 1)
                    403 -> toastLiveData.value = "잔류신청 조회 권한이 없습니다."
                    else -> toastLiveData.value = "오류코드: ${response.code()}"
                }
            }
            override fun onFailure(call: Call<ApplyStayingModel>, t: Throwable) { toastLiveData.value = "오류가 발생했습니다." }
        })
    }

    fun setStayingData(currentItem: Int){
        selectedView.value?.let { originalColorLiveEvent.call() }
        pageStatusLiveData.value = currentItem
        changeColorLiveEvent.call()
    }
}