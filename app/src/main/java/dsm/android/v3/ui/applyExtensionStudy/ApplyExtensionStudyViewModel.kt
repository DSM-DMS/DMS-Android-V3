package dsm.android.v3.ui.applyExtensionStudy

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.view.View
import android.widget.TextView
import dsm.android.v3.connecter.Connecter.api
import dsm.android.v3.model.ExtensionModel
import dsm.android.v3.util.LifecycleCallback
import dsm.android.v3.util.getToken
import dsm.android.v3.util.saveToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplyExtensionStudyViewModel(val contract: ApplyExtensionStudyContract, classView: View, timeView: View): ViewModel(), LifecycleCallback{

    private val time = MutableLiveData<Int>()
    private val classNum = MutableLiveData<Int>()

    private val clickedTimeView = MutableLiveData<View>()
    private val clickedClassView = MutableLiveData<View>()

    init {
        applyExtensionStudyClickClass(classView, 1)
        applyExtensionStudyClickTime(timeView, 11)
    }

    override fun apply(event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_START -> loadMap()
        }
    }

    fun applyExtensionStudyClickBack() = contract.backApplyMenu()

    fun applyExtensionStudyClickTime(textView: View, time: Int){
        if (this.time.value != time){
            clickedTimeView.value?.let { contract.originTextViewColor(it as TextView) }
            contract.changeTextViewColor(textView as TextView)
            clickedTimeView.value = textView
            this.time.value = time
        }
        loadMap()
    }

    fun applyExtensionStudyClickClass(textView: View, classNum: Int){
        if (this.classNum.value != classNum){
            clickedClassView.value?.let { contract.originTextViewColor(it as TextView) }
            contract.changeTextViewColor(textView as TextView)
            clickedClassView.value = textView
            this.classNum.value = classNum
        }
        loadMap()
    }

    fun applyExtensionStudyClickCancel(view: View){
        time.value?.let {
            api.deleteExtension(getToken(view.context), time.value!!).enqueue(object: Callback<Unit>{
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        contract.createShortToast(
                            when(response.code()){
                                200 -> "연장취소에 성공했습니다."
                                403 -> "연장취소 권한이 없습니다."
                                409 -> "연장취소 가능시간이 아닙니다."
                                500 -> "로그인이 필요합니다."
                                else -> "오류코드: ${response.code()}"
                        })
                        loadMap()
                    }
                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        contract.createShortToast("오류가 발생했습니다.")
                    }
                })
        }
    }

    fun applyExtensionStudyClickApply(view: View){
        contract.selectSeatIndex?.let {
            api.applyExtension(getToken(view.context), time.value!!, hashMapOf("classNum" to classNum.value!!, "seatNum" to it))
                .enqueue(object: Callback<Unit>{
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        contract.createShortToast(
                            when(response.code()){
                                201 -> "연장신청에 성공했습니다."
                                205 -> "신청 불가 지역입니다."
                                403 -> "연장신청 권한이 없습니다."
                                409 -> "연장신청 가능시간이 아닙니다."
                                500 -> "로그인이 필요합니다."
                                else -> "오류 코드: ${response.code()}"
                        })
                        loadMap()
                    }
                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        contract.createShortToast("오류가 발생했습니다.")
                    }
                })
        }
    }

    fun loadMap(){
        time.value?.let {
            classNum.value?.let {
                api.getMap(time.value!!, classNum.value!!).enqueue(object: Callback<ExtensionModel> {
                    override fun onResponse(call: Call<ExtensionModel>, response: Response<ExtensionModel>) {
                        when(response.code()){
                            200 -> contract.drawMap(response.body()!!.map)
                            403 -> contract.createShortToast("연장신청 권한이 없습니다.")
                            else -> contract.createShortToast("오류 코드: ${response.code()}")
                        }
                    }
                    override fun onFailure(call: Call<ExtensionModel>, t: Throwable) {
                        contract.createShortToast("오류가 발생했습니다.")
                    }
                })
            }
        }
    }
}