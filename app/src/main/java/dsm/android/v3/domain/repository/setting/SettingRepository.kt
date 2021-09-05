package dsm.android.v3.domain.repository.setting

interface SettingRepository {
    fun loadDarkMode(): Int
    fun saveDarkMode(type:Int)
}