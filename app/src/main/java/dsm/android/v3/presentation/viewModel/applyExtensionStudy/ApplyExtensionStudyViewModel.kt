package dsm.android.v3.presentation.viewModel.applyExtensionStudy

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.view.View
import android.widget.TextView
import dsm.android.v3.domain.repository.applyExtensionStudy.ApplyExtensionStudyRepository
import dsm.android.v3.util.BaseViewModel
import dsm.android.v3.util.LifecycleCallback
import dsm.android.v3.util.SingleLiveEvent

class ApplyExtensionStudyViewModel(val applyExtensionStudyRepository: ApplyExtensionStudyRepository): BaseViewModel(), LifecycleCallback{

    private val time = MutableLiveData<Int>().apply { value = 12 }
    private val classNum = MutableLiveData<Int>().apply { value = 1 }

    val clickedTimeView = MutableLiveData<View>()
    val clickedClassView = MutableLiveData<View>()

    val topLocation = MutableLiveData<String>()
    val leftLocation = MutableLiveData<String>()
    val rightLocation = MutableLiveData<String>()

    val toastLiveData = MutableLiveData<String>()
    val selectedSeatIndex = MutableLiveData<Int>()
    val drawMapLiveData = MutableLiveData<ArrayList<ArrayList<Any>>>()
    val originalColorLiveData = MutableLiveData<TextView>()

    val backApplyMenuLiveEvent = SingleLiveEvent<Any>()

    init {
        setSideName(classNum.value!!)
        loadMap()
    }

    override fun apply(event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_START -> loadMap()
        }
    }

    fun setSideName(classNum: Int) {
        when(classNum){
            1, 2, 3, 4 -> {
                topLocation.value = "칠판"
                leftLocation.value = "창문"
                rightLocation.value = "복도"
            }
            7, 9, 10 -> {
                topLocation.value = "창문"
                leftLocation.value = "옆방"
                rightLocation.value = "계단"
            }
            6, 8 -> {
                topLocation.value = "창문"
                leftLocation.value = "학교"
                rightLocation.value = "옆방"
            }
            5, 11 -> {
                topLocation.value = ""
                leftLocation.value = ""
                rightLocation.value = ""
            }
        }
    }

    fun applyExtensionStudyClickBack() = backApplyMenuLiveEvent.call()

    fun applyExtensionStudyClickTime(textView: View, time: Int){
        if (this.time.value != time){
            clickedTimeView.value?.let { originalColorLiveData.value = it as TextView }
            clickedTimeView.value = textView
            this.time.value = time
        }
        loadMap()
    }

    fun applyExtensionStudyClickClass(textView: View, classNum: Int){

        setSideName(classNum)
        if (this.classNum.value != classNum){
            clickedClassView.value?.let { originalColorLiveData.value = it as TextView }
            clickedClassView.value = textView
            this.classNum.value = classNum
        }
        loadMap()
    }

    fun applyExtensionStudyClickCancel(view: View){
        time.value?.let {
            add(applyExtensionStudyRepository.deleteExtension(time.value!!)
                .subscribe ({ response ->
                    when(response.code()){
                        200 -> "연장취소에 성공했습니다."
                        204 -> "연장신청을 하지 않았습니다."
                        403 -> "연장취소 권한이 없습니다."
                        409 -> "연장취소 가능시간이 아닙니다."
                        500 -> "로그인이 필요합니다."
                        else -> "오류코드: ${response.code()}"
                    }
                    loadMap()
                }, {
                    toastLiveData.value = "오류가 발생했습니다."
                }))
        }
    }

    fun applyExtensionStudyClickApply(view: View){
        selectedSeatIndex.value?.let {
            add(applyExtensionStudyRepository.applyExtension(time.value!!, hashMapOf("classNum" to classNum.value!!, "seatNum" to it))
                .subscribe({response ->
                    toastLiveData.value = (
                        when(response.code()){
                            201 -> "연장신청에 성공했습니다."
                            205 -> "신청 불가 지역입니다."
                            403 -> "연장신청 권한이 없습니다."
                            409 -> "연장신청 가능시간이 아닙니다."
                            500 -> "로그인이 필요합니다."
                            else -> "오류 코드: ${response.code()}"
                        })
                    loadMap()
                }, {
                    toastLiveData.value = "오류가 발생했습니다."
                }))
        }
    }

    fun loadMap(){
        time.value?.let {
            classNum.value?.let {
                add(applyExtensionStudyRepository.getMap(time.value!!, classNum.value!!)
                    .subscribe({ response ->
                        when(response.code()){
                            200 -> drawMapLiveData.value = response.body()!!.map
                            403 -> toastLiveData.value = "연장신청 권한이 없습니다."
                            else -> toastLiveData.value = "오류 코드: ${response.code()}"
                        }
                    }, {
                    toastLiveData.value = "오류가 발생했습니다."
                }))
            }
        }
    }
}