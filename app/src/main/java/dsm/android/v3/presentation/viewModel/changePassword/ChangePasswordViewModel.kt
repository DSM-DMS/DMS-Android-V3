package dsm.android.v3.presentation.viewModel.changePassword

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import dsm.android.v3.domain.entity.Auth
import dsm.android.v3.data.local.database.AuthDatabase
import dsm.android.v3.domain.repository.changePassword.ChangePasswordRepository
import dsm.android.v3.util.BaseViewModel
import dsm.android.v3.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordViewModel(val changePasswordRepository: ChangePasswordRepository) : BaseViewModel() {

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
        add(changePasswordRepository.changePw(
            hashMapOf(
                "currentPassword" to currentPassword.value,
                "newPassword" to newPassword.value
            )
        ).subscribe({ response ->
            when (response.code()) {
                201 -> {
                    CoroutineScope(Dispatchers.IO).launch { changePasswordRepository.saveDb(newPassword.value!!) }
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
        }, {
            errorLiveEvent.call()
            activityFinishLiveEvent.call()
        }))
    }

    fun onCloseBtnClicked() {
        activityFinishLiveEvent.call()
    }

    fun MutableLiveData<String>.isValueBlank() = value.isNullOrBlank()

}