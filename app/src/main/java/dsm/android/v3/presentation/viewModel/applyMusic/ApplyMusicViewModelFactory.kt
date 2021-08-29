package dsm.android.v3.presentation.viewModel.applyMusic

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dsm.android.v3.domain.repository.applyMusic.ApplyMusicRepository

class ApplyMusicViewModelFactory(private val applyMusicRepository: ApplyMusicRepository, val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            modelClass.getConstructor(ApplyMusicRepository::class.java, Application::class.java).newInstance(applyMusicRepository, application)
}