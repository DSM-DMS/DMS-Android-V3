package dsm.android.v3.ui.signIn

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import dsm.android.v3.connecter.Connecter
import dsm.android.v3.model.AuthModel
import dsm.android.v3.util.SingleLiveEvent
import dsm.android.v3.util.saveToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel(val app: Application) : AndroidViewModel(app) {

    val signInId = MutableLiveData<String>()
    val signInPw = MutableLiveData<String>()

    val successToastLiveEvent = SingleLiveEvent<Any>()

    val failedToastLiveEvent = SingleLiveEvent<Any>()
    val networkToastLiveEvent = SingleLiveEvent<Any>()
    val loginSuccessLiveEvent = SingleLiveEvent<Any>()
    val doRegisterLiveEvent = SingleLiveEvent<Any>()

    val isIdFocused = MutableLiveData<Boolean>()
    val isPasswordFocused = MutableLiveData<Boolean>()

    val btnColorSet = MediatorLiveData<Boolean>().apply {
        addSource(signInId) { this.value = !signInId.isValueBlank() && !signInPw.isValueBlank() }
        addSource(signInPw) { this.value = !signInId.isValueBlank() && !signInPw.isValueBlank() }
    }


    fun doSignIn() {
        val auth = Auth(signInId.value!!, signInPw.value!!)
        Connecter.api.signIn(hashMapOf("id" to signInId.value, "password" to signInPw.value))
            .enqueue(object : Callback<AuthModel> {
                override fun onResponse(call: Call<AuthModel>, response: Response<AuthModel>) {
                    when (response.code()) {
                        200 -> {
                            CoroutineScope(Dispatchers.Main).launch {
                                launch(Dispatchers.IO) {
                                    AuthDatabase.getInstance(app.baseContext)
                                        ?.getAuthDao()?.insert(auth)
                                }
                                successToastLiveEvent.call()
                                response.body()?.token?.let { saveToken(app.baseContext, it) }
                                response.body()?.refreshToken?.let { saveToken(app.baseContext, it, false) }
                                loginSuccessLiveEvent.call()
                            }
                        }
                        204 -> failedToastLiveEvent.call()
                        else -> networkToastLiveEvent.call()
                    }
                }

                override fun onFailure(call: Call<AuthModel>, t: Throwable) {
                    networkToastLiveEvent.call()
                }
            })
    }

    fun toSignUpBtn() = doRegisterLiveEvent.call()

    fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()

}