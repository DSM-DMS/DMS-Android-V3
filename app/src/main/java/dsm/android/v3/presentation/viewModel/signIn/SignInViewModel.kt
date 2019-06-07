package dsm.android.v3.presentation.viewModel.signIn

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import dsm.android.v3.domain.repository.signIn.SignInRepository
import dsm.android.v3.util.BaseViewModel
import dsm.android.v3.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel(val signInRepository: SignInRepository) : BaseViewModel() {

    val signInId = MutableLiveData<String>()
    val signInPw = MutableLiveData<String>()

    val loginSuccessLiveEvent = SingleLiveEvent<Any>()
    val networkErrorLiveEvent = SingleLiveEvent<Any>()
    val loginFailedLiveEvent = SingleLiveEvent<Any>()
    val doRegisterLiveEvent = SingleLiveEvent<Any>()

    val isIdFocused = MutableLiveData<Boolean>()
    val isPasswordFocused = MutableLiveData<Boolean>()

    val btnColorSet = MediatorLiveData<Boolean>().apply {
        addSource(signInId) { this.value = !signInId.isValueBlank() && !signInPw.isValueBlank() }
        addSource(signInPw) { this.value = !signInId.isValueBlank() && !signInPw.isValueBlank() }
    }


    fun doSignIn() {
        add(signInRepository.signIn(hashMapOf("id" to signInId.value, "password" to signInPw.value))
            .subscribe({ response ->
                when (response.code()) {
                    200 -> {
                        response.body()?.token?.let { signInRepository.saveToken(it, true) }
                        response.body()?.refreshToken?.let { signInRepository.saveToken(it, false) }
                        loginSuccessLiveEvent.call()
                    }
                    204 -> loginFailedLiveEvent.call()
                    else -> networkErrorLiveEvent.call()
                }
            }, {
                networkErrorLiveEvent.call()
            }))
    }

    fun toSignUpBtn() = doRegisterLiveEvent.call()

    fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()

}