package dsm.android.v3.presentation.viewModel.changePassword

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dsm.android.v3.domain.repository.changePassword.ChangePasswordRepository

class ChangePasswordViewModelFactory(val changePasswordRepository: ChangePasswordRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            modelClass.getConstructor(ChangePasswordRepository::class.java).newInstance(changePasswordRepository)
}