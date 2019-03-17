package dsm.android.v3.ui.mypage

import android.animation.ValueAnimator
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
    val goodPointText = MutableLiveData<String>().apply { value = "0" }
    val badPointText = MutableLiveData<String>().apply { value = "0" }
    val adviceText = MutableLiveData<String>()

    override fun apply(event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_START -> {
                api.getBasicInfo(getToken(context)).enqueue(object: Callback<MyPageInfoModel> {
                    override fun onResponse(call: Call<MyPageInfoModel>, response: Response<MyPageInfoModel>) {
                        when(response.code()){
                            200 -> {
                                nameText.value = response.body()!!.name
                                infoText.value = createStudentNumber(response.body()!!.number)
                                adviceText.value = response.body()!!.advice
                                startCountAnimation(response.body()!!.goodPoint, response.body()!!.badPoint)
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

    fun startCountAnimation(merit: Int, demerit: Int) {
        val meritAnimator = ValueAnimator.ofInt(0, merit)
        val demeritAnimator = ValueAnimator.ofInt(0, demerit)
        meritAnimator.duration = 500
        demeritAnimator.duration = 500

        meritAnimator.addUpdateListener { animation -> goodPointText.value = animation.animatedValue.toString() }
        demeritAnimator.addUpdateListener { animation -> badPointText.value = animation.animatedValue.toString() }
        meritAnimator.start()
        demeritAnimator.start()
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