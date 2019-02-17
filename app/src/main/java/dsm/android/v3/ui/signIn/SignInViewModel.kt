package dsm.android.v3.ui.signIn

import android.arch.lifecycle.*
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import dsm.android.v3.connecter.Connecter
import dsm.android.v3.connecter.Res
import dsm.android.v3.model.AuthModel
import dsm.android.v3.util.saveToken


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

    fun inputData(view : View){
        signIn(view)
        Log.e("asdfasdfasdf", "asdfasdfasdfasdf")
    }

    fun signIn(view : View) {
        Connecter.api.signIn(hashMapOf("id" to signInId, "password" to signInPw))
            .enqueue(object : Res<AuthModel>(view.context, false) {
                override fun callBack(code: Int, body: AuthModel?) {
                    navigator.showToast(when (code) {
                        201 -> {
                            saveToken(context, body!!.token)
                            saveToken(context, body!!.refreshToken!!, false)
                            navigator.intentToRegister()
                            "로그인 성공"
                        }
                        401 -> "로그인 실패"
                        else -> "오류 : $code"
                    })
                }
            })
    }

    fun toSignUpBtn() {
        navigator.intentToRegister()
    }

    fun MutableLiveData<String>.isValueBlank() =
        this.value.isNullOrBlank()

    //    fun onClickEdit(v : View) {
//        navigator.confirmEditText(v)
//    }

}

