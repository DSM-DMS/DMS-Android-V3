package dsm.android.v3.util

import android.content.Context
import android.support.v7.app.AppCompatDelegate
import dsm.android.v3.data.local.shared.LocalStorage

class ThemeUtil(private val localStorage: LocalStorage) {

    fun applyTheme(type:Int) {
        when (type) {
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                localStorage.saveInt("darkMode",1)
            }
            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                localStorage.saveInt("darkMode",2)
            }
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                localStorage.saveInt("darkMode",0)
            }
        }
    }

}