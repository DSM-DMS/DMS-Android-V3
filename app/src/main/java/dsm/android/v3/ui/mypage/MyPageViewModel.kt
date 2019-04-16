package dsm.android.v3.ui.mypage

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import dsm.android.v3.connecter.api
import dsm.android.v3.model.MyPageInfoModel
import dsm.android.v3.util.LifecycleCallback
import dsm.android.v3.util.SingleLiveEvent
import dsm.android.v3.util.getToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageViewModel(val app: Application): AndroidViewModel(app), LifecycleCallback{

    val nameText = MutableLiveData<String>()
    val infoText = MutableLiveData<String>()
    val goodPointText = MutableLiveData<String>().apply { value = "0" }
    val badPointText = MutableLiveData<String>().apply { value = "0" }
    val adviceText = MutableLiveData<String>()

    val goodPoint = MutableLiveData<Int>()
    val badPoint = MutableLiveData<Int>()

    val pointCountAnimatorEvent = SingleLiveEvent<Any>()
    val showInstitutionReportEvent = SingleLiveEvent<Any>()
    val intentQuestionResearchEvent = SingleLiveEvent<Any>()
    val showBugReportEvent = SingleLiveEvent<Any>()
    val showLogoutEvent = SingleLiveEvent<Any>()
    val intentPasswordChangeEvent = SingleLiveEvent<Any>()
    val intentMeritHistoryEvent = SingleLiveEvent<Any>()
    val intentIntroDevelopersEvent = SingleLiveEvent<Any>()

    override fun apply(event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_RESUME -> {
                api.getBasicInfo(getToken(app.applicationContext)).enqueue(object: Callback<MyPageInfoModel> {
                    override fun onResponse(call: Call<MyPageInfoModel>, response: Response<MyPageInfoModel>) {
                        when(response.code()){
                            200 -> {
                                nameText.value = response.body()!!.name
                                infoText.value = createStudentNumber(response.body()!!.number)
                                adviceText.value = response.body()!!.advice
                                goodPoint.value = response.body()!!.goodPoint
                                badPoint.value = response.body()!!.badPoint
                                pointCountAnimatorEvent.call()
                            }
                            403 -> adviceText.value = "마이페이지 조회 권한이 없습니다."
                            500 -> adviceText.value = "로그인이 필요합니다."
                            else -> adviceText.value = "오류코드: ${response.code()}"
                        }
                    }

                    override fun onFailure(call: Call<MyPageInfoModel>, t: Throwable) {
                        adviceText.value = "오류가 발생했습니다."
                    }
                })
            }
        }
    }

    fun createStudentNumber(num: Int): String{
        val numString = num.toString()
        if (numString.length == 4){
            val studentGrade = numString.substring(0, 1)
            val studentClass = numString.substring(1, 2)
            val studentNumber = numString.substring(2, 4).toInt().toString()
            return "${studentGrade}학년 ${studentClass}반 ${studentNumber}번"
        } else {
            return "학번: $num"
        }
    }

    fun clickEnterInstitutionReport() = showInstitutionReportEvent.call()

    fun clickEnterQuestionResearch() = intentQuestionResearchEvent.call()

    fun clickEnterBugReport() = showBugReportEvent.call()

    fun clickEnterLogout() = showLogoutEvent.call()

    fun clickEnterPasswordChange() = intentPasswordChangeEvent.call()

    fun clickEnterMeritHistory() = intentMeritHistoryEvent.call()

    fun clickEnterIntroDevelopers() = intentIntroDevelopersEvent.call()

}