package dsm.android.v3.presentation.viewModel.signIn

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dsm.android.v3.domain.repository.signIn.SignInRepository

class SignInViewModelFactory(val signInRepository: SignInRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            modelClass.getConstructor(SignInRepository::class.java).newInstance(signInRepository)
}