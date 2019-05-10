package dsm.android.v3.presentation.viewModel.applyExtensionStudy

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import dsm.android.v3.domain.repository.applyExtensionStudy.ApplyExtensionStudyRepository

class ApplyExtensionStudyViewModelFactory(val applyExtensionRepository: ApplyExtensionStudyRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            modelClass.getConstructor(ApplyExtensionStudyRepository::class.java).newInstance(applyExtensionRepository)
}