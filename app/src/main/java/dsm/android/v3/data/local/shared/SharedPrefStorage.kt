package dsm.android.v3.data.local.shared

import android.content.Context
import android.content.SharedPreferences
import io.reactivex.Observable

class SharedPrefStorage(val context: Context) : LocalStorage {
    override fun saveToken(token: String, access: Boolean) =
        getPref(context).edit().let {
            it.putString(getKey(access), token)
            it.apply()
        }

    override fun getToken(isAccess: Boolean): String =
        "Bearer " + getPref(context).getString(getKey(isAccess), "")

    override fun removeToken() =
        getPref(context).edit().let {
            it.remove(getKey(true))
            it.apply()
        }

    override fun saveInt(key: String, content: Int) {
        getPref(context).edit().let {
            it.putInt(key, content)
            it.apply()
        }
    }

    override fun getInt(key: String): Int = getPref(context).getInt(key, 0)

    private fun getPref(context: Context): SharedPreferences =
        context.getSharedPreferences("pref", Context.MODE_PRIVATE)

    private fun getKey(isAccess: Boolean): String = if (isAccess) "Access" else "Refresh"
}