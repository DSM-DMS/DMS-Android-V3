package dsm.android.v3.presentation.viewModel.setting

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import dsm.android.v3.domain.repository.setting.SettingRepository
import dsm.android.v3.util.SingleLiveEvent

class SettingViewModel(private val settingRepository: SettingRepository):ViewModel() {
    val type = SingleLiveEvent<Int>()

    init {
        type.value = settingRepository.loadDarkMode()
    }

    fun clickType(clickedType:Int){
        settingRepository.saveDarkMode(clickedType)
        type.value = clickedType
    }

}