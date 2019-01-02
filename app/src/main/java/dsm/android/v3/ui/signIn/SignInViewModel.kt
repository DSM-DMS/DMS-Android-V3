package dsm.android.v3.ui.signIn

import android.arch.lifecycle.*
import android.arch.lifecycle.ViewModel
import android.util.Log

class SignInViewModel : ViewModel()  {

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

    fun inputData() {

    }

    fun MutableLiveData<String>.isValueBlank() =
        this.value.isNullOrBlank()

}
