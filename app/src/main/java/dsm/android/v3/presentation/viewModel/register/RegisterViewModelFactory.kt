package dsm.android.v3.presentation.viewModel.register

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dsm.android.v3.domain.repository.register.RegisterRepository

class RegisterViewModelFactory(val registerRepository: RegisterRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            modelClass.getConstructor(RegisterRepository::class.java).newInstance(registerRepository)
}