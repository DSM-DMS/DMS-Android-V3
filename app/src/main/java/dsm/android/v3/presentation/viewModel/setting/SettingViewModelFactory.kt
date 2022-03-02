package dsm.android.v3.presentation.viewModel.setting

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import dsm.android.v3.domain.repository.setting.SettingRepository

class SettingViewModelFactory(private val settingRepository: SettingRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(SettingRepository::class.java).newInstance(settingRepository)
}