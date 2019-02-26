package dsm.android.v3.ui.register

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.JsonObject
import dsm.android.v3.connecter.Connecter
import dsm.android.v3.model.AuthModel
import dsm.android.v3.util.SingleLiveEvent
import dsm.android.v3.util.saveToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(val registerNavigator: RegisterNavigator) : ViewModel()  {

    val registerConfirmCode = MutableLiveData<String>()
    val registerId = MutableLiveData<String>()
    val registerPw = MutableLiveData<String>()
    val registerPwComfirm = MutableLiveData<String>()
    val activityFinishLiveEvent = SingleLiveEvent<Any>()

    val btnColorSet = MediatorLiveData<Boolean>().apply {
        addSource(registerConfirmCode) {
            this.value = !registerConfirmCode.isValueBlank() && !registerId.isValueBlank()
                    && !registerPw.isValueBlank() && !registerPwComfirm.isValueBlank()
        }
        addSource(registerId) {
            this.value = !registerConfirmCode.isValueBlank() && !registerId.isValueBlank()
                    && !registerPw.isValueBlank() && !registerPwComfirm.isValueBlank()

        }
        addSource(registerPw) {
            this.value = !registerConfirmCode.isValueBlank() && !registerId.isValueBlank()
                    && !registerPw.isValueBlank() && !registerPwComfirm.isValueBlank()
        }
        addSource(registerPwComfirm) {
            this.value = !registerConfirmCode.isValueBlank() && !registerId.isValueBlank()
                    && !registerPw.isValueBlank() && !registerPwComfirm.isValueBlank()
        }
    }

    fun doSignUp(view: View) {
        if (registerPw.value == registerPwComfirm.value) {
            val json = JsonObject().apply {
                addProperty("uuid", registerConfirmCode.value)
                addProperty("id", registerId.value)
                addProperty("password", registerPw.value)
            }
            Connecter.api.signUp(json).enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    when (response.code()) {
                        201 -> {
                            Toast.makeText(view.context, "회원가입 성공", Toast.LENGTH_SHORT).show()
                            activityFinishLiveEvent.call()
                            registerNavigator.intentToLogin()
                        }
                        204 -> {
                            Toast.makeText(view.context, "유효하지 않은 uuid", Toast.LENGTH_SHORT).show()
                            activityFinishLiveEvent.call()
                        }
                        205 -> {
                            Toast.makeText(view.context, "중복된 ID", Toast.LENGTH_SHORT).show()
                            activityFinishLiveEvent.call()
                        }
                    }
                }
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Toast.makeText(view.context, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    activityFinishLiveEvent.call()
                }
            })
        } else{
            Toast.makeText(view.context, "비밀번호가 서로 다릅니다", Toast.LENGTH_SHORT).show()
        }
    }

    fun MutableLiveData<String>.isValueBlank() =
        this.value.isNullOrBlank()

}
