package dsm.android.v3.domain.repository.setting

import dsm.android.v3.data.local.shared.LocalStorage

class SettingRepositoryImpl(val localStorage: LocalStorage) : SettingRepository {
    override fun loadDarkMode(): Int = localStorage.getInt("darkMode")

    override fun saveDarkMode(type: Int) {
        localStorage.saveInt("darkMode",type)
    }
}