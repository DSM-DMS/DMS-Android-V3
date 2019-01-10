package dsm.android.v3.ui.register

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class RegisterViewModel(val registerNavigator: RegisterNavigator) : ViewModel()  {

    val registerConfirmCode = MutableLiveData<String>()
    val registerId = MutableLiveData<String>()
    val registerPw = MutableLiveData<String>()
    val registerPwComfirm = MutableLiveData<String>()

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

    fun inputData() {
        registerNavigator.signUpPost()
    }

    fun MutableLiveData<String>.isValueBlank() =
        this.value.isNullOrBlank()

}