package dsm.android.v3.ui.signIn

import android.arch.lifecycle.*
import android.arch.lifecycle.ViewModel
import android.databinding.BindingAdapter
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import dsm.android.v3.util.SingleLiveEvent


class SignInViewModel(val navigator: SignInNavigator) : ViewModel()  {

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

    fun onClickEdit(v : View) {
        navigator.confirmEditText(v)
    }

    fun toSignUpBtn(){
        navigator.intentToRegister()
    }

    fun MutableLiveData<String>.isValueBlank() =
        this.value.isNullOrBlank()

}
