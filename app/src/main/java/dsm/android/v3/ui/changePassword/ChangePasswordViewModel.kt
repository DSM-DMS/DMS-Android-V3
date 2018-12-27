package dsm.android.v3.ui.changePassword

import android.arch.lifecycle.*
import android.util.Log
import dsm.android.v3.util.SingleLiveEvent

class ChangePasswordViewModel : ViewModel() {

    val originPassword = MutableLiveData<String>()
    val newPassword = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()
    val activityFinishLiveEvent = SingleLiveEvent<Any>()
    val inputStatus = MediatorLiveData<Boolean>().apply {
        addSource(originPassword) {
            this.value = !originPassword.isValueBlank() && newPassword.value == confirmPassword.value &&
                    !newPassword.isValueBlank() && !confirmPassword.isValueBlank()
        }
        addSource(newPassword) {
            this.value = !originPassword.isValueBlank() && newPassword.value == confirmPassword.value &&
                    !newPassword.isValueBlank() && !confirmPassword.isValueBlank()
        }
        addSource(confirmPassword) {
            this.value = !originPassword.isValueBlank() && newPassword.value == confirmPassword.value &&
                    !newPassword.isValueBlank() && !confirmPassword.isValueBlank()
        }
    }

    fun changePassword() {
        activityFinishLiveEvent.call()
    }

    fun onCloseBtnClicked(){
        activityFinishLiveEvent.call()
    }

    fun MutableLiveData<String>.isValueBlank() =
        this.value.isNullOrBlank()


}