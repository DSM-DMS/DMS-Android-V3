package dsm.android.v3.presentation.viewModel.changePassword

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import dsm.android.v3.domain.entity.Auth
import dsm.android.v3.data.local.database.AuthDatabase
import dsm.android.v3.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordViewModel(val app: Application) : AndroidViewModel(app) {

    val currentPassword = MutableLiveData<String>()
    val newPassword = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()
    val activityFinishLiveEvent = SingleLiveEvent<Any>()
    val inputStatus = MediatorLiveData<Boolean>().apply {
        addSource(currentPassword) {
            this.value = !currentPassword.isValueBlank() && newPassword.value == confirmPassword.value &&
                    !newPassword.isValueBlank() && !confirmPassword.isValueBlank()
        }
        addSource(newPassword) {
            this.value = !currentPassword.isValueBlank() && newPassword.value == confirmPassword.value &&
                    !newPassword.isValueBlank() && !confirmPassword.isValueBlank()
        }
        addSource(confirmPassword) {
            this.value = !currentPassword.isValueBlank() && newPassword.value == confirmPassword.value &&
                    !newPassword.isValueBlank() && !confirmPassword.isValueBlank()
        }
    }

    val changeSuccessLiveEvent = SingleLiveEvent<Any>()
    val samePasswordLiveEvent = SingleLiveEvent<Any>()
    val errorLiveEvent = SingleLiveEvent<Any>()

    fun changePassword() {
        Connecter.api.changePw(
            getToken(app.baseContext),
            hashMapOf(
                "currentPassword" to currentPassword.value,
                "newPassword" to newPassword.value
            )
        ).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                when (response.code()) {
                    201 -> {
                        CoroutineScope(Dispatchers.IO).launch {
                            AuthDatabase.getInstance(app.baseContext)?.getAuthDao().let {
                                it?.insert(Auth(it.getAuth().id, newPassword.value!!))
                            }
                        }
                        changeSuccessLiveEvent.call()
                        activityFinishLiveEvent.call()

                    }
                    205 -> {
                        samePasswordLiveEvent.call()
                    }
                    403 -> {
                        errorLiveEvent.call()
                        activityFinishLiveEvent.call()
                    }
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                errorLiveEvent.call()
                activityFinishLiveEvent.call()
            }

        })
    }

    fun onCloseBtnClicked() {
        activityFinishLiveEvent.call()
    }

    fun MutableLiveData<String>.isValueBlank() = value.isNullOrBlank()

}