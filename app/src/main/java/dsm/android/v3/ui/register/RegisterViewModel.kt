package dsm.android.v3.ui.register

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import dsm.android.v3.connecter.Connecter
import dsm.android.v3.util.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    val registerConfirmCode = MutableLiveData<String>()
    val registerId = MutableLiveData<String>()
    val registerPw = MutableLiveData<String>()
    val registerPwConfirm = MutableLiveData<String>()


    val btnColorSet = MediatorLiveData<Boolean>().apply {
        addSource(registerConfirmCode) {
            this.value = !registerConfirmCode.isValueBlank() && !registerId.isValueBlank()
                    && !registerPw.isValueBlank() && !registerPwConfirm.isValueBlank()
        }
        addSource(registerId) {
            this.value = !registerConfirmCode.isValueBlank() && !registerId.isValueBlank()
                    && !registerPw.isValueBlank() && !registerPwConfirm.isValueBlank()

        }
        addSource(registerPw) {
            this.value = !registerConfirmCode.isValueBlank() && !registerId.isValueBlank()
                    && !registerPw.isValueBlank() && !registerPwConfirm.isValueBlank()
        }
        addSource(registerPwConfirm) {
            this.value = !registerConfirmCode.isValueBlank() && !registerId.isValueBlank()
                    && !registerPw.isValueBlank() && !registerPwConfirm.isValueBlank()
        }
    }

    val registerFinishedLiveEvent = SingleLiveEvent<Any>()
    val wrongUuidLiveEvent = SingleLiveEvent<Any>()
    val sameIdLiveEvent = SingleLiveEvent<Any>()
    val wrongPwLiveEvent = SingleLiveEvent<Any>()
    val badNetworkLiveEvent = SingleLiveEvent<Any>()

    fun doSignUp() {
        if (registerPw.value == registerPwConfirm.value) {
            Connecter.api.signUp(
                hashMapOf(
                    "uuid" to registerConfirmCode.value,
                    "id" to registerId.value,
                    "password" to registerPw.value
                )
            ).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    when (response.code()) {
                        201 -> registerFinishedLiveEvent.call()
                        204 -> wrongUuidLiveEvent.call()
                        205 -> sameIdLiveEvent.call()
                        else -> badNetworkLiveEvent.call()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    badNetworkLiveEvent.call()
                }
            })
        } else {
            wrongPwLiveEvent.call()
        }
    }

    fun MutableLiveData<String>.isValueBlank() = value.isNullOrBlank()

}
