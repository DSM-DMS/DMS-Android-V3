package dsm.android.v3.ui.activity.register

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class RegisterViewModelFactory(private val registerNavigator: RegisterNavigator) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RegisterNavigator::class.java).newInstance(registerNavigator)
    }
}