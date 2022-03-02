package dsm.android.v3.data.local.shared

interface LocalStorage {
    fun saveToken(token: String, access: Boolean)
    fun getToken(isAccess: Boolean): String
    fun removeToken()

    fun saveInt(key: String, content: Int)
    fun getInt(key: String): Int
}