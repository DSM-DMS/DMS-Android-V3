package dsm.android.v3.data.local.shared

interface LocalStorage {
    fun saveToken(token: String, access: Boolean)

    fun getToken(isAccess: Boolean = true): String

    fun removeToken()
}