package dsm.android.v3.ui.signIn

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class SignInViewModelFactory(private val signInNavigator: SignInNavigator) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SignInNavigator::class.java).newInstance(signInNavigator)
    }
}