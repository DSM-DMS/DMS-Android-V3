package dsm.android.v3.data.local.shared

interface LocalStorage {
    fun saveToken(token: String)
    fun getToken(): String
    fun removeToken()
}