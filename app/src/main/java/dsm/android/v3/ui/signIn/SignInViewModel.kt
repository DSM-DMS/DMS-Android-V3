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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel() : ViewModel() {

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


    fun doSignIn(view: View) {
        val auth = Auth(signInId.value!!, signInPw.value!!)
        Connecter.api.signIn(hashMapOf("id" to signInId.value, "password" to signInPw.value))
            .enqueue(object : Callback<AuthModel> {
                override fun onResponse(call: Call<AuthModel>, response: Response<AuthModel>) {
                    when (response.code()) {
                        200 -> {
                            CoroutineScope(Dispatchers.Main).launch {
                                launch(Dispatchers.IO) {
                                    AuthDatabase.getInstance(view.context)
                                        ?.getAuthDao()?.insert(auth)
                                }
                                successToastLiveEvent.call()
                                response.body()?.token?.let { saveToken(view.context, it) }
                                response.body()?.refreshToken?.let { saveToken(view.context, it, false) }
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