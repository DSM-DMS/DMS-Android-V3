package dsm.android.v3.ui.signIn

import android.arch.lifecycle.*
import android.arch.lifecycle.ViewModel
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

class SignInViewModel(val navigator: SignInNavigator) : ViewModel() {

    val signInId = MutableLiveData<String>()
    val signInPw = MutableLiveData<String>()

    val btnColorSet = MediatorLiveData<Boolean>().apply {
        addSource(signInId) {
            this.value = !signInId.isValueBlank() && !signInPw.isValueBlank()
        }
        addSource(signInPw) {
            this.value = !signInId.isValueBlank() && !signInPw.isValueBlank()
        }
    }

    fun doSignIn(view: View) {


        val json = JsonObject().apply {
            addProperty("id", signInId.value)
            addProperty("password", signInPw.value)
        }
        Connecter.api.signIn(json).enqueue(object : Callback<AuthModel> {
            override fun onResponse(call: Call<AuthModel>, response: Response<AuthModel>) {
                when (response.code()) {
                    200 -> {
                        Toast.makeText(view.context, "로그인 성공", Toast.LENGTH_SHORT).show()
                        saveToken(view.context,response.body()!!.token)
                        saveToken(view.context, response.body()!!.refreshToken!!, false)
                        navigator.intentToMain()
                    }
                    401 -> {
                        Toast.makeText(view.context, "로그인 실패", Toast.LENGTH_SHORT).show()
                    } else -> Toast.makeText(view.context, "오류코드: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<AuthModel>, t: Throwable) {
                Toast.makeText(view.context, "네트워크 상태를 확인해주세요", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun toSignUpBtn() = navigator.intentToRegister()

    fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()

}