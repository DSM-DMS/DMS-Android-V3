package dsm.android.v3.ui.applyStaying

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.view.View
import dsm.android.v3.connecter.api
import dsm.android.v3.util.getToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplyStayingViewModel(val contract: ApplyStayingContract): ViewModel(){

    private val selectedView = MutableLiveData<View>()
    val pageStatusLiveData = MutableLiveData<Int>().apply { value = 0 }

    init { getStayInfo() }

    fun applyBtnClick(view: View) {
        api.applyStay(getToken(view.context), hashMapOf("value" to pageStatusLiveData.value!! + 1)).enqueue(object: Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                contract.createShortToast(
                    when(response.code()){
                        201 -> "잔류신청에 성공했습니다."
                        204 -> "잔류신청 가능시간이 아닙니다."
                        403 -> "잔류신청 권한이 없습니다."
                        else -> "오류코드: ${response.code()}"
                })
                getStayInfo()
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                contract.createShortToast("오류가 발생했습니다.")
            }
        })
    }

    fun getStayInfo(){
        api.getStayInfo(getToken(contract as Context)).enqueue(object: Callback<ApplyStayingModel> {
            override fun onResponse(call: Call<ApplyStayingModel>, response: Response<ApplyStayingModel>) {
                when(response.code()){
                    200 -> setStayingData(response.body()!!.value - 1)
                    403 -> contract.createShortToast("잔류신청 조회 권한이 없습니다.")
                    else -> contract.createShortToast("오류코드: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<ApplyStayingModel>, t: Throwable) { contract.createShortToast("오류가 발생했습니다.") }
        })
    }

    fun setStayingData(currentItem: Int){
        selectedView.value?.let { contract.originalColor(it) }
        selectedView.value = contract.viewGroup.getChildAt(currentItem)
        pageStatusLiveData.value = currentItem
        contract.changeColor(selectedView.value!!)
    }
}