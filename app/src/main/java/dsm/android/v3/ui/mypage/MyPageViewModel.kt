package dsm.android.v3.ui.mypage

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import dsm.android.v3.connecter.api
import dsm.android.v3.model.MyPageInfoModel
import dsm.android.v3.util.LifecycleCallback
import dsm.android.v3.util.getToken
import dsm.android.v3.util.saveToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class MyPageViewModel(val contract: MyPageContract, val context: Context): ViewModel(), LifecycleCallback{

    val nameText = MutableLiveData<String>()
    val infoText = MutableLiveData<String>()
    val adviceText = MutableLiveData<String>()

    init {
        saveToken(context, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1NTIzMTQxOTEsIm5iZiI6MTU1MjMxNDE5MSwianRpIjoiMWE3MzY3ZjktZjJiMC00MWU1LWJlOWYtZTcxNGY5Mzg5NGY5IiwiZXhwIjoxNTg4MzEwNTkxLCJpZGVudGl0eSI6InRlc3QiLCJmcmVzaCI6ZmFsc2UsInR5cGUiOiJhY2Nlc3MifQ.bQTPxQ4ifG2tomigLaGCBlAga0jvCEEFGB6b2QO52qM")
    }

    override fun apply(event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_START -> {
                api.getBasicInfo(getToken(context)).enqueue(object: Callback<MyPageInfoModel> {
                    override fun onResponse(call: Call<MyPageInfoModel>, response: Response<MyPageInfoModel>) {
                        when(response.code()){
                            200 -> {
                                nameText.value = response.body()!!.name
                                infoText.value = createStudentNumber(response.body()!!.number)
                                contract.startCountAnimation(response.body()!!.goodPoint, response.body()!!.badPoint)
                                adviceText.value = response.body()!!.advice
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

    fun clickEnterInstitutionReport() = contract.showDialogInstitutionReport()

    fun clickEnterQuestionResearch() = contract.intentQuestionResearch()

    fun clickEnterBugReport() = contract.showDialogBugReport()

    fun clickEnterLogout() = contract.showDialogLogout()

    fun clickEnterPasswordChange() = contract.intentPasswordChange()

    fun clickEnterMeritHistory() = contract.intentMeriteHistory()

    fun clickEnterIntroDevelopers() = contract.intentintroDevelopers()

}